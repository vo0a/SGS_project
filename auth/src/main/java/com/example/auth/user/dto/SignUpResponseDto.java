package com.example.auth.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private String uuid;
    private String name;

    @Builder
    public SignUpResponseDto(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}