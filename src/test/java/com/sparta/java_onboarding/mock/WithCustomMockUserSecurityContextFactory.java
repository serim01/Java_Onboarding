package com.sparta.java_onboarding.mock;

import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import com.sparta.java_onboarding.domain.auth.entity.UserAuthorityName;

public class WithCustomMockUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser> {
	@Override
	public SecurityContext createSecurityContext(WithCustomMockUser annotation) {
		String username = annotation.username();
		Authentication auth = new UsernamePasswordAuthenticationToken(username, "",
			Collections.singleton(new SimpleGrantedAuthority(UserAuthorityName.USER.name())));
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(auth);
		return context;
	}
}