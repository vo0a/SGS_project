package com.example.auth.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUuid(String uuid);

    Optional<User> findByEmail(String email);
}
