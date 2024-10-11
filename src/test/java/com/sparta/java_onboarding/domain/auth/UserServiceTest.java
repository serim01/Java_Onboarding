package com.sparta.java_onboarding.domain.auth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sparta.java_onboarding.common.security.JwtService;
import com.sparta.java_onboarding.domain.auth.dto.SignRequestDto;
import com.sparta.java_onboarding.domain.auth.dto.SignResponseDto;
import com.sparta.java_onboarding.domain.auth.dto.SignupRequestDto;
import com.sparta.java_onboarding.domain.auth.dto.SignupResponseDto;
import com.sparta.java_onboarding.domain.auth.entity.User;
import com.sparta.java_onboarding.domain.auth.entity.UserAuthorityName;

class UserServiceTest {
	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private JwtService jwtService;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void signup() {
		SignupRequestDto requestDto = new SignupRequestDto("testuser", "Test User", "password");
		User user = User.builder().username("testuser").password("password").nickname("Test User").build();

		when(userRepository.findUserByUsername("testuser")).thenReturn(Optional.empty());
		when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
		when(userRepository.save(any(User.class))).thenReturn(user);

		SignupResponseDto responseDto = userService.signup(requestDto);

		assertNotNull(responseDto);
		assertEquals("testuser", responseDto.getUsername());
		assertEquals("Test User", responseDto.getNickname());

	}

	@Test
	void sign() {
		SignRequestDto requestDto = new SignRequestDto("testuser", "password");
		User user = User.builder()
			.username("testuser")
			.password("encodedPassword")
			.nickname("Test User")
			.authorityName(UserAuthorityName.USER)
			.build();

		when(userRepository.findUserByUsername("testuser")).thenReturn(Optional.of(user));
		when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
		doReturn("access_token").when(jwtService).generateAccessToken(any(UserAuthorityName.class), anyString());
		doReturn("refresh_token").when(jwtService).generateRefreshToken(anyString());
		
		SignResponseDto responseDto = userService.sign(requestDto);

		assertNotNull(responseDto);
		assertEquals("access_token", responseDto.getToken());

	}

}