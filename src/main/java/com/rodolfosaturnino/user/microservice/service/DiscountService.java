package com.rodolfosaturnino.user.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodolfosaturnino.user.microservice.dataacessobject.DiscountDTO;
import com.rodolfosaturnino.user.microservice.dataacessobject.ProductDTO;
import com.rodolfosaturnino.user.microservice.domain.User;
import com.rodolfosaturnino.user.microservice.exception.EntityNotFoundException;
import com.rodolfosaturnino.user.microservice.service.discountchain.DiscountBuilder;

@Service
public class DiscountService {

	@Autowired
	private UserService userService;
	
	public DiscountDTO calculateDiscount(Long productId, Long userId) throws EntityNotFoundException {
		User user = userService.findUser(userId);
		ProductDTO product = getProductWithGrpc(productId);
		return getDiscount(product, user);
	}

	public DiscountDTO getDiscount(ProductDTO product, User user) {
		DiscountBuilder discountBuilder = new DiscountBuilder();
		return discountBuilder.getDiscountDTO(product, user);
	}

	public ProductDTO getProductWithGrpc(Long productId) {
		return new ProductDTO(Long.toString(productId),19000,"product","product test",new DiscountDTO());
	}
}
