package com.example.membership.admin.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String uuid;

    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    private String name;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private short role;

    // 1 : 활성상태, 0 : 비활성상태
    @Column(nullable = false, columnDefinition = "TINYINT")
    private short active;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}