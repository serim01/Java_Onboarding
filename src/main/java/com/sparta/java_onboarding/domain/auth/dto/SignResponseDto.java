package com.sparta.java_onboarding.domain.auth.dto;

import com.sparta.java_onboarding.domain.auth.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignResponseDto {
	String token;

	@Builder
	public SignResponseDto(User user) {
		this.token = user.getRefreshToken();
	}
}
