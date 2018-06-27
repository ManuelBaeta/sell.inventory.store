package com.manolo.sell.inventory.rest;

import java.util.Collection;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.manolo.sell.inventory.domain.User;
import com.manolo.sell.inventory.domain.exception.EntityForeignKeyNotFound;
import com.manolo.sell.inventory.domain.exception.EntityNotFoundException;
import com.manolo.sell.inventory.domain.exception.UnableToInsertException;
import com.manolo.sell.inventory.domain.rest.OrderRequest;
import com.manolo.sell.inventory.domain.rest.OrderResponse;
import com.manolo.sell.inventory.domain.rest.PageRequest;
import com.manolo.sell.inventory.service.OrderService;
import com.manolo.sell.inventory.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserResource {

	private final Logger log = LoggerFactory.getLogger(UserResource.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/{userId}/order")
	public ResponseEntity<OrderResponse> placeOrder(@PathVariable int userId, @Valid @RequestBody OrderRequest orderRequest) 
			throws EntityNotFoundException, EntityForeignKeyNotFound {
		OrderResponse orderResponse = orderService.placeOrder(orderRequest.setUserId(userId));
		return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
	}
	
	@PostMapping()
	public ResponseEntity<User> addUser(@Valid @RequestBody User userRequest) throws UnableToInsertException {
		log.info("Requesting to add user: {}", userRequest);
		User addedUser = userService.addUser(userRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedUser) ;
	}
	
	@PutMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUserAccount(@PathVariable int userId, @RequestParam("status") User.STATES status) throws EntityNotFoundException {
		log.info("Requesting to update userId: {}, to state: {}", userId, status);
		userService.updateUserStatus(userId, status);
	}
	
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserAccount(@PathVariable int userId) throws EntityNotFoundException {
		log.info("Requesting to delete userId: {}", userId);
		userService.deleteUser(userId);
	}
	
	@DeleteMapping("/{userId}/order/{orderId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable int userId, @PathVariable int orderId) {
		log.info("Requesting to delete order {} for user: {}", orderId, userId);
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable int userId) throws EntityNotFoundException {
		log.info("Requesting User with userId: {}", userId);
		return userService.getUserById(userId);
	}
	
	@GetMapping()
	public Collection<User> getUsers(@RequestParam(name="pagenumber", defaultValue="0") int pageNumber, @RequestParam(name="pagesize", defaultValue="5") int pageSize) {
		log.info("Requesting all users. Page number: {}, page size: {}", pageNumber, pageSize);
		return userService.getUsers(new PageRequest(pageNumber, pageSize));
	}
}
