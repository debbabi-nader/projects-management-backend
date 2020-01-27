package io.isamm.projectsmanagement.services;

import io.isamm.projectsmanagement.dtos.SignInCredentialsDto;
import io.isamm.projectsmanagement.dtos.TokenDto;
import io.isamm.projectsmanagement.entities.UserEntity;


public interface AuthenticationService {

	public UserEntity signUp(UserEntity userEntity);
	
	public TokenDto signIn(SignInCredentialsDto signInCredentialsDto);
	
	public void verifyToken(TokenDto tokenDto);

}
