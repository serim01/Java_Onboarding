package com.sparta.java_onboarding.domain.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.java_onboarding.domain.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByUsername(String username);
}
