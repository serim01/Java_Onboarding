package com.sparta.java_onboarding.domain.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.java_onboarding.domain.auth.dto.SignupRequestDto;
import com.sparta.java_onboarding.domain.auth.dto.SignupResponseDto;
import com.sparta.java_onboarding.domain.auth.entity.User;
import com.sparta.java_onboarding.domain.auth.entity.UserAuthorityName;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public SignupResponseDto signup(SignupRequestDto requestDto) {
		// 사용자 등록
		User saveUser = createUser(requestDto);
		userRepository.save(saveUser);
		return SignupResponseDto.builder().user(saveUser).build();
	}

	private User createUser(SignupRequestDto requestDto) {
		return User.builder()
			.username(requestDto.getUsername())
			.password(passwordEncoder.encode(requestDto.getPassword()))
			.nickname(requestDto.getNickname())
			.authorityName(UserAuthorityName.USER)
			.build();
	}
}
