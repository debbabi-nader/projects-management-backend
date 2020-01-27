package io.isamm.projectsmanagement.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.isamm.projectsmanagement.dtos.SignInCredentialsDto;
import io.isamm.projectsmanagement.dtos.TokenDto;
import io.isamm.projectsmanagement.entities.UserEntity;
import io.isamm.projectsmanagement.services.AuthenticationService;


@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@Autowired
	public AuthenticationController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public UserEntity signUp(@Valid @RequestBody UserEntity userEntity) {
		return this.authenticationService.signUp(userEntity);
	}

	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public TokenDto signIn(@Valid @RequestBody SignInCredentialsDto signInCredentialsDto) {
		return this.authenticationService.signIn(signInCredentialsDto);
	}

	@RequestMapping(value = "/token-verification", method = RequestMethod.POST)
	public void verifyToken(@RequestBody TokenDto tokenDto) {
		this.authenticationService.verifyToken(tokenDto);
	}
	
}
