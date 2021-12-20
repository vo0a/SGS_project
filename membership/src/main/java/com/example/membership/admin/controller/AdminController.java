package com.example.membership.admin.controller;

import com.example.membership.admin.dto.UserDetailsResponseDto;
import com.example.membership.admin.dto.UserResponseDto;
import com.example.membership.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/membership/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllMembers() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllMembers());
    }

    @GetMapping("/users/{uuid}")
    public ResponseEntity<UserDetailsResponseDto> getMemberDetails(@PathVariable("uuid") String uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getMemberDetails(uuid));
    }
}
