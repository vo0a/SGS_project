package com.example.auth.user.service;

import com.example.auth.exception.EmailSigninFailedException;
import com.example.auth.user.domain.Role;
import com.example.auth.user.domain.User;
import com.example.auth.user.domain.UserRepository;
import com.example.auth.user.dto.SignInRequestDto;
import com.example.auth.user.dto.SignInResponseDto;
import com.example.auth.user.dto.SignUpRequestDto;
import com.example.auth.user.dto.SignUpResponseDto;
import com.example.auth.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return userRepository.findByUuid(uuid).orElseThrow(); // UserNotFoundException::new
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

        String token = jwtTokenProvider.createToken(user.getUuid(), user.getName(),user.getRole().getName());

        return SignInResponseDto.builder()
                .uuid(user.getUuid())
                .token(token)
                .name(user.getName())
                .role(user.getRole())
                .build();
    }

    public String withdraw(String token) {
        log.info("==========withdraw=============");
        if (jwtTokenProvider.validateToken(token)) {
            Optional<User> user = userRepository.findByUuid(jwtTokenProvider.getUserPk(token));//.orElseThrow(); // UserDoseNotExistException::new

            log.info(user.toString());

            // todo : user 상태 변경
            // todo : redis 에 저장, uuid, date
            return "Delete success : 30일 이내에 재접속하면 탈퇴되지 않습니다.";
        } else {
//            throw new InvalidTokendException("Expired Token");
            return "Expired Token : 로그인이 필요합니다.";
        }
    }
}
