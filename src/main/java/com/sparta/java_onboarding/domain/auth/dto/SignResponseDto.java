package com.sparta.java_onboarding.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignResponseDto {
	String token;
}
