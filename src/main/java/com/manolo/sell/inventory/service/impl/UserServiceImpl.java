package com.manolo.sell.inventory.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.manolo.sell.inventory.domain.User;
import com.manolo.sell.inventory.domain.User.STATES;
import com.manolo.sell.inventory.domain.exception.EntityNotFoundException;
import com.manolo.sell.inventory.domain.exception.UnableToInsertException;
import com.manolo.sell.inventory.domain.rest.PageRequest;
import com.manolo.sell.inventory.repository.UserRepository;
import com.manolo.sell.inventory.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User userRequest) throws UnableToInsertException {
		log.info("[UserService] Adding new User ");
		try {
			return userRepository.save(userRequest.makeInsertable());
		} catch(Exception ex) {
			log.info("[UserService] Unable to add User. Dni already present: {}. Caught exception, it will "
					+ "be re-thrown as UnableToInsertException ", userRequest.getDni());
			throw new UnableToInsertException(String.format("Unable to add User. Dni already present: %s", userRequest.getDni()));
		}
	}

	@Override
	public void updateUserStatus(int userId, STATES status) throws EntityNotFoundException {
		log.info("[UserService] Updating status");
		Optional<User> optUser = userRepository.findById(userId);
		
		User user = optUser
				.orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find user with id: %s", userId)));
		
		user.setState(status);
		userRepository.save(user);
	}

	@Override
	public void deleteUser(int userId) throws EntityNotFoundException {
		log.info("[UserService] Deleting user");
		try {
			userRepository.deleteById(userId);
		} catch(EmptyResultDataAccessException ex) {
			throw new EntityNotFoundException(String.format("Unable to delete user with id: %s", userId));
		}
	}

	@Override
	public User getUserById(int userId) throws EntityNotFoundException {
		log.info("[UserService] Getting user");
		Optional<User> optUser = userRepository.findById(userId);
		
		return optUser.orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find user with id: %s", userId)));
	}

	@Override
	public Collection<User> getUsers(PageRequest pageRequest) {
		log.info("Getting all users");
		return userRepository.findAll(org.springframework.data.domain.PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize())).getContent();			
	}

}
