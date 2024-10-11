package com.sparta.java_onboarding.domain.auth;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.java_onboarding.common.exception.CustomException;
import com.sparta.java_onboarding.common.exception.ErrorCode;
import com.sparta.java_onboarding.common.security.JwtService;
import com.sparta.java_onboarding.domain.auth.dto.SignRequestDto;
import com.sparta.java_onboarding.domain.auth.dto.SignResponseDto;
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
	private final JwtService jwtService;

	@Transactional
	public SignupResponseDto signup(SignupRequestDto requestDto) {
		// 사용자 등록
		User saveUser = createUser(requestDto);
		userRepository.save(saveUser);
		return SignupResponseDto.builder().user(saveUser).build();
	}

	private User createUser(SignupRequestDto requestDto) {

		Optional<User> user = userRepository.findUserByUsername(requestDto.getUsername());
		if (user.isPresent()) {
			throw new CustomException(ErrorCode.DUPLICATE_UESR);
		}

		return User.builder()
			.username(requestDto.getUsername())
			.password(passwordEncoder.encode(requestDto.getPassword()))
			.nickname(requestDto.getNickname())
			.authorityName(UserAuthorityName.USER)
			.build();
	}

	@Transactional
	public SignResponseDto sign(SignRequestDto requestDto) {
		User user = findUserByUsername(requestDto.getUsername());

		if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
			throw new CustomException(ErrorCode.PASSWORD_INVALID);
		}

		String accessToken = jwtService.generateAccessToken(user.getAuthorityName(), user.getUsername());
		String refreshToken = jwtService.generateRefreshToken(user.getUsername());

		user.updateRefreshToken(refreshToken);

		jwtService.setRefreshTokenAtCookie(refreshToken);
		jwtService.setAccessTokenAtHeader(accessToken);

		return SignResponseDto.builder().token(accessToken).build();
	}

	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username)
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
	}
}
