package com.example.auth.user.service;

import com.example.auth.exception.EmailSigninFailedException;
import com.example.auth.exception.InvalidTokendException;
import com.example.auth.exception.UserNotFoundException;
import com.example.auth.user.domain.Role;
import com.example.auth.user.domain.User;
import com.example.auth.user.domain.UserRepository;
import com.example.auth.user.dto.SignInRequestDto;
import com.example.auth.user.dto.SignInResponseDto;
import com.example.auth.user.dto.SignUpRequestDto;
import com.example.auth.user.dto.SignUpResponseDto;
import com.example.auth.exception.UserDoseNotExistException;
import com.example.auth.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return userRepository.findByUuid(uuid).orElseThrow(UserNotFoundException::new);
    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        User user = userRepository.save(User.builder()
            .name(signUpRequestDto.getName())
            .email(signUpRequestDto.getEmail())
            .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
            .role(Role.GUEST)
            .active((short) 1)
            .build());

        return SignUpResponseDto.builder()
            .uuid(user.getUuid())
            .name(user.getName())
            .build();
    }

    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        User user = userRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(EmailSigninFailedException::new);

        if (!passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword()))
            throw new EmailSigninFailedException("비밀번호가 맞지 않습니다.");

        if (user.getActive() == (short) 0) {
            // 캐시 확인 - 탈퇴 신청일로 30일 이내면 active user로 변경 / 이후면 접근 불가
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            String date = valueOperations.get(user.getUuid());
            Duration duration = Duration.between(LocalDateTime.parse(date), LocalDateTime.now()); // 초단위 계산 : 60 * 60 * 24 * 30가 30일
            if( duration.getSeconds() < 2592000) {
                user.toggleActive();
                userRepository.save(user);
            }
            else {
                throw new UserDoseNotExistException("탈퇴한 사용자입니다.");
            }
        }

        String token = jwtTokenProvider.createToken(user.getUuid(), user.getName(), user.getRole().getName());

        return SignInResponseDto.builder()
            .uuid(user.getUuid())
            .token(token)
            .name(user.getName())
            .role(user.getRole())
            .build();
    }

    public String withdraw(String token) throws InvalidTokendException {
        if (jwtTokenProvider.validateToken(token)) {
            User user = userRepository.findByUuid(jwtTokenProvider.getUserPk(token)).orElseThrow(() -> new UserDoseNotExistException("사용자 정보가 삭제되었습니다."));

            // 활성 상태 토글 : 활성(1) -> 비활성(0)
            log.info(user.toString());
            System.out.println(user.getActive());
            user.toggleActive();
            System.out.println(user.getActive());
            userRepository.save(user);

            // 유저 캐싱
            String key = user.getUuid();
            String date = LocalDateTime.now().toString();
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, date);

            return "Delete success : " + user.getName() + "님 30일 이내에 재접속하면 탈퇴되지 않습니다.";
        } else {
            throw new InvalidTokendException("Expired Token");
        }
    }
}
