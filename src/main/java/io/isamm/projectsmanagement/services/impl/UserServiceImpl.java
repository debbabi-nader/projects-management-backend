package io.isamm.projectsmanagement.services.impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.isamm.projectsmanagement.entities.UserEntity;
import io.isamm.projectsmanagement.exceptions.AccountNotFoundException;
import io.isamm.projectsmanagement.exceptions.InvalidEmailAddressException;
import io.isamm.projectsmanagement.exceptions.WeakPasswordException;
import io.isamm.projectsmanagement.repositories.UserRepository;
import io.isamm.projectsmanagement.services.UserService;


@Service
public class UserServiceImpl extends GenericServiceImpl<UserEntity> implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserEntity getUserByEmail(String email) {
		
		UserEntity userEntity = this.userRepository.getUserByEmail(email);
		if (userEntity == null)
			throw new AccountNotFoundException(email);
		return userEntity;

	}

	@Transactional
	@Override
	public UserEntity create(UserEntity userEntity) {
		
		if (!Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(userEntity.getEmail()).matches())
			throw new InvalidEmailAddressException();
		
		if (!Pattern.compile("^.*(?=.{8,})((?=.*[!@#$%^&*()\\-_=+{};:,<.>]){1})(?=.*\\d)((?=.*[a-z]){1})((?=.*[A-Z]){1}).*$").matcher(userEntity.getPassword()).matches())
			throw new WeakPasswordException();

		userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
		userEntity.setIsActive(true);
		
		return super.create(userEntity);

	}
	
}
