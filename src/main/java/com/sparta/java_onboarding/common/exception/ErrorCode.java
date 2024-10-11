package com.sparta.java_onboarding.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	FAIL(500, "실패했습니다."),
	ACCESS_DINIED(403, "접근 권한이 없습니다."),
	DUPLICATE_UESR(400, "중복된 사용자가 존재합니다."),
	USER_NOT_FOUND(400, "해당 유저를 찾지 못했습니다."),
	PASSWORD_INVALID(400, "입력하신 비밀번호가 일치하지 않습니다.");

	private int status;
	private String msg;
}