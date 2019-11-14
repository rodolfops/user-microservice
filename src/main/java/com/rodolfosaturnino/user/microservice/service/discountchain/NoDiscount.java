package com.rodolfosaturnino.user.microservice.service.discountchain;

import com.rodolfosaturnino.user.microservice.dataacessobject.DiscountDTO;
import com.rodolfosaturnino.user.microservice.domain.Product;
import com.rodolfosaturnino.user.microservice.domain.User;

public class NoDiscount extends DiscountHandler {

	@Override
	public DiscountDTO getDiscount(Product product, User user) {
		double discount = 0;
		return new DiscountDTO(discount, new Integer(0));
	}

}
