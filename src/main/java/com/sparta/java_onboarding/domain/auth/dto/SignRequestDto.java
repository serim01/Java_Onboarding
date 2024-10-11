package com.sparta.java_onboarding.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignRequestDto {
	String username;
	String password;

	public SignRequestDto(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
