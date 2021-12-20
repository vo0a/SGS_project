package com.example.auth.user.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "com.example.auth.util.UUIDGenerator"
    )
    @Column(columnDefinition = "VARCHAR(20)")
    private String uuid;

    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    private String name;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    // 해당 데이터를 테이블의 컬럼과 매핑 시키지 않는다.
    @Transient
    private Collection<SimpleGrantedAuthority> authorities;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Role role;

    // 1 : 활성상태, 0 : 비활성상태
    @Column(nullable = false, columnDefinition = "TINYINT")
    private short active;

    @Builder
    public User(String name, String email, String password, Role role, short active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
