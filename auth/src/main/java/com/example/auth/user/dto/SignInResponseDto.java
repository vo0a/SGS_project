package com.example.auth.user.dto;

import com.example.auth.user.domain.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResponseDto {

    private String uuid;
    private String token;
    private String name;
    private Role role;

    @Builder
    public SignInResponseDto(String uuid, String token, String name, Role role) {
        this.uuid = uuid;
        this.token = token;
        this.name = name;
        this.role = role;
    }
}
