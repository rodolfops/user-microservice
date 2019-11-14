package com.rodolfosaturnino.user.microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rodolfosaturnino.user.microservice.domain.User;
import com.rodolfosaturnino.user.microservice.exception.ConstraintsViolationException;
import com.rodolfosaturnino.user.microservice.exception.EntityNotFoundException;
import com.rodolfosaturnino.user.microservice.repository.UserRepository;

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserService.class);

	private final UserRepository userRepository;
	
	@Autowired
	public UserService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public List<User> findAll(Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable);
		if (page.hasContent()) {
			return page.getContent();
		}
		return new ArrayList<User>();
	}

	@Transactional
	public User save(User userToCreate) throws ConstraintsViolationException {
		User user = null;
		try {
			user = userRepository.save(userToCreate);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("ConstraintsViolationException while creating a user: {}",
					user, e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return user;
	}

	@Transactional
	public User update(String id, User userToUpdate)
			throws EntityNotFoundException {
		return userRepository
				.findById(id)
				.map(user -> {
					if(userToUpdate.getFirstName() != null && !userToUpdate.getFirstName().isEmpty()) {
						user.setFirstName(userToUpdate.getFirstName());
					}
					if(userToUpdate.getLastName() != null && !userToUpdate.getLastName().isEmpty()) {
						user.setLastName(userToUpdate.getLastName());
					}
					if(userToUpdate.getDateOfBirth() != null) {
						user.setDateOfBirth(userToUpdate.getDateOfBirth());
					}
					return userRepository.save(user);
				})
				.orElseThrow(
						() -> new EntityNotFoundException(
								"Could not find entity with id: " + id));
	}

	@Transactional
	public void delete(String id) throws EntityNotFoundException {
		userRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(
						"Could not find entity with id: " + id));
		userRepository.deleteById(id);
	}

	@Transactional
	public User findUser(String id) throws EntityNotFoundException {
		return userRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(
						"Could not find entity with id: " + id));
	}
}
