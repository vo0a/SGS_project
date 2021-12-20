package com.example.auth.user.dto;

import lombok.Getter;

@Getter
public class SignInRequestDto {

    private String email;
    private String password;
}
