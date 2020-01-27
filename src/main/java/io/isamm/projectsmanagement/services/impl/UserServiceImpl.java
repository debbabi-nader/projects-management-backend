package io.isamm.projectsmanagement.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.isamm.projectsmanagement.entities.UserEntity;
import io.isamm.projectsmanagement.exceptions.AccountNotFoundException;
import io.isamm.projectsmanagement.repositories.UserRepository;
import io.isamm.projectsmanagement.services.UserService;


@Service
public class UserServiceImpl extends GenericServiceImpl<UserEntity> implements UserService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public UserEntity getUserByEmail(String email) {
		
		UserEntity userEntity = this.userRepository.getUserByEmail(email);
		if (userEntity == null)
			throw new AccountNotFoundException(email);
		return userEntity;

	}

}
