package com.rodolfosaturnino.user.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodolfosaturnino.user.microservice.dataacessobject.DiscountDTO;
import com.rodolfosaturnino.user.microservice.dataacessobject.ProductDTO;
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
		ProductDTO product = getProductWithGrpc(productId);
		return getDiscount(product, user);
	}

	public double getDiscount(ProductDTO product, User user) {
		//check if it is user birthday 
		//check if it is black friday by last friday of november
		//return it at max 10%
		return 0.0;
	}

	public ProductDTO getProductWithGrpc(Long productId) {
		return new ProductDTO(Long.toString(productId),19000,"product","product test",new DiscountDTO());
	}
}
