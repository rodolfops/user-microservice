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
public class ProductDiscountService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductDiscountService.class);

	private final UserService userService;
	private final ProductService productService;

	@Autowired
	public ProductDiscountService(UserService userService,
			ProductService productService) {
		this.userService = userService;
		this.productService = productService;
	}
	
	public DiscountDTO calculateDiscount(String productId, String userId) {
		try {
			User user = null;
			if(userId != null && !userId.isEmpty()) {
				user = userService.findUser(userId);
			} 
			Product product = productService.findProduct(productId);
			return getDiscount(product, user);
		} catch (EntityNotFoundException e) {
			log.error(e.getMessage());
		}
		return new DiscountDTO(0.0, new Integer(0));
	}

	public DiscountDTO getDiscount(Product product, User user) {
		DiscountBuilder discountBuilder = new DiscountBuilder();
		return discountBuilder.getDiscountDTO(product, user);
	}

}
