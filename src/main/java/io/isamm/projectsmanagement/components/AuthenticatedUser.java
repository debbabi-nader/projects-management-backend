package io.isamm.projectsmanagement.components;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.isamm.projectsmanagement.entities.UserEntity;


public class AuthenticatedUser implements Authentication {

	private static final long serialVersionUID = -4639113024954876656L;
	
	private final UserEntity user;
	
	public AuthenticatedUser(UserEntity user) {
		super();
		this.user = user;
	}

	@Override
	public String getName() {
		return this.user.getFirstName() + " " + this.user.getLastName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.user.getProfileType().getProfileType()));
	}

	@Override
	public Object getCredentials() {
		return this.user.getPassword();
	}

	@Override
	public Object getDetails() {
		return this.user;
	}

	@Override
	public Object getPrincipal() {
		return this.user.getEmail();
	}

	@Override
	public boolean isAuthenticated() {
		return false;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		
	}

}
