package com.rodolfosaturnino.user.microservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodolfosaturnino.user.microservice.dataacessobject.DiscountDTO;
import com.rodolfosaturnino.user.microservice.domain.Product;
import com.rodolfosaturnino.user.microservice.domain.User;
import com.rodolfosaturnino.user.microservice.exception.EntityNotFoundException;
import com.rodolfosaturnino.user.microservice.service.discountchain.DiscountBuilder;

@Service
public class DiscountService {
	
	private static final Logger log = LoggerFactory.getLogger(DiscountService.class);

	private final UserService userService;
	private final ProductService productService;

	@Autowired
	public DiscountService(UserService userService,
			ProductService productService) {
		this.userService = userService;
		this.productService = productService;
	}
	
	public DiscountDTO calculateDiscount(String productId, String userId) throws EntityNotFoundException {
		log.info("Searching user with id: "+userId);
		User user = userService.findUser(userId);
		log.info("Searching product with id: "+productId);
		Product product = productService.findProduct(productId);
		log.info("===getting discount===");
		return getDiscount(product, user);
	}

	public DiscountDTO getDiscount(Product product, User user) {
		DiscountBuilder discountBuilder = new DiscountBuilder();
		return discountBuilder.getDiscountDTO(product, user);
	}

}
