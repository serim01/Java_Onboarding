package com.sparta.java_onboarding.domain.auth.dto;

import com.sparta.java_onboarding.domain.auth.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupResponseDto {
	private String username;
	private String nickname;
	private String authority;

	@Builder
	public SignupResponseDto(User user) {
		this.username = user.getUsername();
		this.nickname = user.getNickname();
		this.authority = user.getAuthorityName().name();
	}
}