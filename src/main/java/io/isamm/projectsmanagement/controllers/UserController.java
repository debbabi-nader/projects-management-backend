package io.isamm.projectsmanagement.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.isamm.projectsmanagement.entities.UserEntity;
import io.isamm.projectsmanagement.services.UserService;


@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping(value = "", params = { "email" }, method = RequestMethod.GET)
	public UserEntity getUserByEmail(@RequestParam(value = "email", required = true) String email) {
		return this.userService.getUserByEmail(email);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<UserEntity> getUsers() {
		return this.userService.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
	}
	
}
