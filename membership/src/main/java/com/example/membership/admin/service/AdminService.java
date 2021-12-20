package com.example.membership.admin.service;

import com.example.membership.admin.domain.User;
import com.example.membership.admin.domain.UserRepository;
import com.example.membership.admin.dto.UserDetailsResponseDto;
import com.example.membership.admin.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableDiscoveryClient
@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;

    public List<UserResponseDto> getAllMembers() {
        List<User> users = userRepository.findAll();
        return UserResponseDto.listOf(users);
    }

    public UserDetailsResponseDto getMemberDetails(String uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow();
        return UserDetailsResponseDto.of(user);
    }
}
