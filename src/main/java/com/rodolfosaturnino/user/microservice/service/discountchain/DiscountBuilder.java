package com.rodolfosaturnino.user.microservice.service.discountchain;

import com.rodolfosaturnino.user.microservice.dataacessobject.DiscountDTO;
import com.rodolfosaturnino.user.microservice.domain.Product;
import com.rodolfosaturnino.user.microservice.domain.User;

public class DiscountBuilder {

	public DiscountHandler discountHandler;
	
	public DiscountBuilder() {
		NoDiscount noDiscount = new NoDiscount();
		BirthdayDiscount birthdayDiscount = new BirthdayDiscount();
		BlackFridayDiscount blackFridayDiscount = new BlackFridayDiscount();
		
		birthdayDiscount.setNext(noDiscount);
		blackFridayDiscount.setNext(birthdayDiscount);
		this.discountHandler = blackFridayDiscount;
	}
	
	public DiscountDTO getDiscountDTO(Product product, User user) {
		return this.discountHandler.getDiscount(product, user);
	}
}
