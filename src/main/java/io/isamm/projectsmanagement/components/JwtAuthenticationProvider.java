package io.isamm.projectsmanagement.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import io.isamm.projectsmanagement.entities.UserEntity;
import io.isamm.projectsmanagement.exceptions.AccountNotFoundException;
import io.isamm.projectsmanagement.services.UserService;
import io.isamm.projectsmanagement.utils.JwtUtil;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final UserService userService;
	
	@Autowired
	public JwtAuthenticationProvider(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String token = (String) authentication.getCredentials();
		if (token == null)
			return null;
		
		String email = JwtUtil.getSubject(token);
		if (email == null)
			return null;
		
		UserEntity userEntity;
		try {
			userEntity = this.userService.getUserByEmail(email);
		} catch (AccountNotFoundException ex) {
			return null;
		}
		
		if (!userEntity.getIsActive())
			return null;
		
		return new AuthenticatedUser(userEntity);

	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return AuthenticatedToken.class.equals(authentication);
		
	}

}
