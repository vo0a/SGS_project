package com.example.membership.admin.dto;

import com.example.membership.admin.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class UserResponseDto {

    private String uuid;
    private String name;
    private String email;
    private short role;
    private short active;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .uuid(user.getUuid())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.getActive())
                .build();
    }

    public static List<UserResponseDto> listOf(List<User> users) {
        return users.stream()
                .map(UserResponseDto::of)
                .collect(Collectors.toList());
    }
}
