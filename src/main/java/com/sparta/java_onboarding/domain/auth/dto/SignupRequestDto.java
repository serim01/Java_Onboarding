package com.sparta.java_onboarding.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
	private String username;
	private String nickname;
	private String password;

	public SignupRequestDto(String username, String nickname, String password) {
		this.username = username;
		this.nickname = nickname;
		this.password = password;
	}
}
