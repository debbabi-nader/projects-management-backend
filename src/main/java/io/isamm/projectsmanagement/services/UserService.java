package io.isamm.projectsmanagement.services;

import io.isamm.projectsmanagement.entities.UserEntity;


public interface UserService extends GenericService<UserEntity> {

	public UserEntity getUserByEmail(String email);

}
