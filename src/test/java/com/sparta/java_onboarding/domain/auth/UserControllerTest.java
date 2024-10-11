package com.sparta.java_onboarding.domain.auth;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.java_onboarding.config.SecurityConfig;
import com.sparta.java_onboarding.domain.auth.dto.SignRequestDto;
import com.sparta.java_onboarding.domain.auth.dto.SignResponseDto;
import com.sparta.java_onboarding.domain.auth.dto.SignupRequestDto;
import com.sparta.java_onboarding.domain.auth.dto.SignupResponseDto;
import com.sparta.java_onboarding.domain.auth.entity.User;
import com.sparta.java_onboarding.domain.auth.entity.UserAuthorityName;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userService;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testSignup() throws Exception {
		SignupRequestDto requestDto = new SignupRequestDto("testuser", "Test User", "password");
		User testUser = User.builder()
			.username(requestDto.getUsername())
			.nickname(requestDto.getNickname())
			.authorityName(UserAuthorityName.USER)
			.build();
		SignupResponseDto responseDto = SignupResponseDto.builder().user(testUser).build();
		when(userService.signup(any(SignupRequestDto.class))).thenReturn(responseDto);
		mockMvc.perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.username").value("testuser"))
			.andExpect(jsonPath("$.nickname").value("Test User"));
	}

	@Test
	void testSign() throws Exception {
		SignRequestDto requestDto = new SignRequestDto("testuser", "password");
		String expectedToken = "token"; // 기대하는 토큰 값 설정
		User testUser = User.builder().username(requestDto.getUsername()).password(requestDto.getPassword()).build();
		testUser.updateRefreshToken(expectedToken);
		// responseDto 생성 시 token 값 설정
		SignResponseDto responseDto = SignResponseDto.builder()
			.token(expectedToken)
			.build();

		when(userService.sign(any(SignRequestDto.class))).thenReturn(responseDto);

		mockMvc.perform(post("/api/sign")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.token").value(expectedToken)); // 기대하는 토큰 값으로 검증
	}

}