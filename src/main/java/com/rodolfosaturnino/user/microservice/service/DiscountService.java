package com.rodolfosaturnino.user.microservice.service;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodolfosaturnino.user.microservice.domain.User;
import com.rodolfosaturnino.user.microservice.exception.EntityNotFoundException;
import com.rodolfosaturnino.user.microservice.repository.UserRepository;

@Service
public class DiscountService {

	@Autowired
	private UserRepository userRepository;
	
	public double getDiscount(Long productId, Long userId) throws EntityNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new EntityNotFoundException(
						"Could not find user with id: " + userId));
		ZonedDateTime today = ZonedDateTime.now();
		
		return 0.0;
	}
}
