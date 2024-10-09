package com.sparta.java_onboarding.domain.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.java_onboarding.domain.auth.dto.SignRequestDto;
import com.sparta.java_onboarding.domain.auth.dto.SignResponseDto;
import com.sparta.java_onboarding.domain.auth.dto.SignupRequestDto;
import com.sparta.java_onboarding.domain.auth.dto.SignupResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto requestDto) {
		SignupResponseDto response = userService.signup(requestDto);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/sign")
	public ResponseEntity<SignResponseDto> sign(@RequestBody SignRequestDto requestDto) {
		SignResponseDto response = userService.sign(requestDto);
		return ResponseEntity.ok().body(response);
	}
}
