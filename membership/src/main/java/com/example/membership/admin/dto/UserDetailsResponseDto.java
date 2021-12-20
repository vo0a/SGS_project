package com.example.membership.admin.dto;

import com.example.membership.admin.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class UserDetailsResponseDto {

    private String uuid;
    private String name;
    private String email;
    private short role;
    private short active;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static UserDetailsResponseDto of(User user) {
        return UserDetailsResponseDto.builder()
                .uuid(user.getUuid())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.getActive())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }
}
