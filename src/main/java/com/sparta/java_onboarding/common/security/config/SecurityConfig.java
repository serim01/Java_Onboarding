package com.sparta.java_onboarding.common.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sparta.java_onboarding.common.security.JwtService;
import com.sparta.java_onboarding.common.security.UserDetailsServiceImpl;
import com.sparta.java_onboarding.common.security.filter.JwtAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	private final UserDetailsServiceImpl userDetailsService;
	private final JwtService jwtService;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() {
		return new JwtAuthorizationFilter(jwtService, userDetailsService);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable());

		http.sessionManagement(
			sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.requestMatchers(
				PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
			.requestMatchers(HttpMethod.POST, "/api/signup").permitAll() // 회원가입 요청 허가
			.requestMatchers(HttpMethod.POST, "/api/sign").permitAll() // 로그인 요청 허가
			.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api-docs/**", "/swagger-resources/**",
				"/webjars/**", "/swagger-ui.html").permitAll() // Swagger 관련 모든 경로 허용
			.anyRequest().authenticated() // 그 외 모든 요청 인증처리
		);

		http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}