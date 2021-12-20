package com.example.auth.user.domain;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST((short)0,"ROLE_GUEST", "준회원"),
    USER ((short)1,"ROLE_USER", "정회원"),
    ADMIN((short)2, "ROLE_ADMIN", "관리자");

    private final short key;
    private final String name;
    private final String description;
}