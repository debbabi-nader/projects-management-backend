package io.isamm.projectsmanagement.services.impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.isamm.projectsmanagement.dtos.SignInCredentialsDto;
import io.isamm.projectsmanagement.dtos.TokenDto;
import io.isamm.projectsmanagement.entities.UserEntity;
import io.isamm.projectsmanagement.exceptions.AccountNotActiveException;
import io.isamm.projectsmanagement.exceptions.InvalidEmailAddressException;
import io.isamm.projectsmanagement.exceptions.UnauthorizedException;
import io.isamm.projectsmanagement.exceptions.WrongPasswordException;
import io.isamm.projectsmanagement.services.AuthenticationService;
import io.isamm.projectsmanagement.services.UserService;
import io.isamm.projectsmanagement.utils.JwtUtil;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	@Override
	public UserEntity signUp(UserEntity userEntity) {
		
		return this.userService.create(userEntity);
	
	}

	@Override
	public TokenDto signIn(SignInCredentialsDto signInCredentialsDto) {
		
		if (!Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(signInCredentialsDto.getEmail()).matches())
			throw new InvalidEmailAddressException();
		
		UserEntity user = this.userService.getUserByEmail(signInCredentialsDto.getEmail());
		
		if (!passwordEncoder.matches(signInCredentialsDto.getPassword(), user.getPassword()))
			throw new WrongPasswordException();
		
		if (!user.getIsActive())
			throw new AccountNotActiveException();
		
		return new TokenDto(JwtUtil.createToken(user.getEmail()));
	
	}

	@Override
	public void verifyToken(TokenDto tokenDto) {
		
		if (!JwtUtil.verifyToken(tokenDto.getAccessToken()))
			throw new UnauthorizedException();

	}

}
