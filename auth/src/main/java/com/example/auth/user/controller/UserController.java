package com.example.auth.user.controller;

import com.example.auth.user.dto.SignInRequestDto;
import com.example.auth.user.dto.SignInResponseDto;
import com.example.auth.user.dto.SignUpRequestDto;
import com.example.auth.user.dto.SignUpResponseDto;
import com.example.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(signUpRequestDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(signInRequestDto));
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestHeader("X-AUTH-TOKEN") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.withdraw(token));
    }
}