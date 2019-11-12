package com.rodolfosaturnino.user.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rodolfosaturnino.user.microservice.domain.User;
import com.rodolfosaturnino.user.microservice.exception.ConstraintsViolationException;
import com.rodolfosaturnino.user.microservice.exception.EntityNotFoundException;
import com.rodolfosaturnino.user.microservice.service.UserService;

@RestController
@RequestMapping(value="/api/users")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> findAll(Pageable pageable) {
		return userService.findAll(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) throws ConstraintsViolationException {
		return userService.save(user);
	}
	
	@PutMapping("/{id}")
	public User update(@PathVariable String id, @RequestBody User user) throws EntityNotFoundException {
		return userService.update(id, user);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) throws EntityNotFoundException {
		userService.delete(id);
	}
}
