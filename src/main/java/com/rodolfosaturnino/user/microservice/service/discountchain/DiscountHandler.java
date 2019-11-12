package com.rodolfosaturnino.user.microservice.service.discountchain;

import com.rodolfosaturnino.user.microservice.dataacessobject.DiscountDTO;
import com.rodolfosaturnino.user.microservice.domain.Product;
import com.rodolfosaturnino.user.microservice.domain.User;


public abstract class DiscountHandler {
	
	protected DiscountHandler next;
	
	public abstract DiscountDTO getDiscount(Product product, User user);

	public void setNext(DiscountHandler next) {
		this.next = next;
	} 
}
