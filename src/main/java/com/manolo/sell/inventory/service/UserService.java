package com.manolo.sell.inventory.service;

import java.util.Collection;

import com.manolo.sell.inventory.domain.User;
import com.manolo.sell.inventory.domain.exception.EntityNotFoundException;
import com.manolo.sell.inventory.domain.exception.UnableToInsertException;
import com.manolo.sell.inventory.domain.rest.PageRequest;

public interface UserService {

	public User addUser(User userRequest) throws UnableToInsertException ;
	
	public void updateUserStatus(int userId, User.STATES status) throws  EntityNotFoundException;
	
	public void deleteUser(int userId) throws EntityNotFoundException ;
	
	public User getUserById(int userId) throws EntityNotFoundException ;
	
	public Collection<User> getUsers(PageRequest pageRequest);
}
