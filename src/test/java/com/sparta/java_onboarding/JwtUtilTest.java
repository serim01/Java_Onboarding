package com.sparta.java_onboarding;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.sparta.java_onboarding.common.security.util.JwtProvider;
import com.sparta.java_onboarding.domain.auth.entity.UserAuthorityName;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JwtUtilTest {

	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER = "Authorization";
	public static final String AUTHORIZATION_KEY = "auth";
	public static final String REFRESH_TOKEN_COOKIE_NAME = "RefreshToken";

	private String secretKey;
	private String base64EncodedSecretKey;

	@BeforeEach
	void setUp() {
		secretKey = "serim'sonboardingproject!updateSecretKeyForJWT256bit";
		base64EncodedSecretKey = JwtProvider.encodeBase64SecretKey(secretKey);
	}

	@Test
	void generateAccessTokenTest() {

		String token = JwtProvider.generateAccessToken("auth", UserAuthorityName.USER.name(), "serim01",
			3000000L, base64EncodedSecretKey);

		System.out.println(token);

		assertNotNull(token);

		assertFalse(token.isEmpty());
	}

	@Test
	void generateRefreshTokenTest() {
		String token = JwtProvider.generateRefreshToken("serim01", 3000000L, base64EncodedSecretKey);

		System.out.println(token);

		assertNotNull(token);

		assertFalse(token.isEmpty());
	}

	@Test
	void validateTokenTest() {

		String accessToken = JwtProvider.generateAccessToken("auth", UserAuthorityName.USER.name(),
			"serim01", 3000000L, base64EncodedSecretKey);
		String refreshToken = JwtProvider.generateRefreshToken("serim01", 3000000L, base64EncodedSecretKey);

		assertTrue(JwtProvider.validateToken(accessToken, base64EncodedSecretKey));
		assertTrue(JwtProvider.validateToken(refreshToken, base64EncodedSecretKey));
	}

}