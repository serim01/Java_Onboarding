package com.sparta.java_onboarding.domain.auth;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return User.builder()
			.username(requestDto.getUsername())
			.password(passwordEncoder.encode(requestDto.getPassword()))
			.nickname(requestDto.getNickname())
			.authorityName(UserAuthorityName.USER)
			.build();
	}

	public SignResponseDto sign(SignRequestDto requestDto) {
		User user = findUserByUsername(requestDto.getUsername());

		String accessToken = jwtService.generateAccessToken(user.getAuthorityName(), user.getUsername());
		String refreshToken = jwtService.generateRefreshToken(user.getUsername());

		user.updateRefreshToken(refreshToken);

		jwtService.setRefreshTokenAtCookie(refreshToken);
		jwtService.setAccessTokenAtHeader(accessToken);

		return SignResponseDto.builder().user(user).build();
	}

	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	}
}
