package com.sparta.java_onboarding.domain.auth.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
	private String username;
	private String nickname;
	private String password;
}
