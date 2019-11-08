package com.rodolfosaturnino.user.microservice.service.discountchain;

import java.time.ZonedDateTime;

import com.rodolfosaturnino.user.microservice.dataacessobject.DiscountDTO;
import com.rodolfosaturnino.user.microservice.dataacessobject.ProductDTO;
import com.rodolfosaturnino.user.microservice.domain.User;

public class BirthdayDiscount extends DiscountHandler {

	private static double BIRTHDAY_DISCOUNT = 0.05; 
	
	@Override
	public DiscountDTO getDiscount(ProductDTO product, User user) {
		if(isUserBirthday(user)) {
			int valueInCents = (int) Math.round(product.getPriceInCents() * BIRTHDAY_DISCOUNT);
			return new DiscountDTO(BIRTHDAY_DISCOUNT, new Integer(valueInCents));
		} else {
			return next.getDiscount(product, user);
		}
	}

	private boolean isUserBirthday(User user) {
		ZonedDateTime dateOfBirth = user.getDateOfBirth();
		if(dateOfBirth == null) return false;
		
		ZonedDateTime today = ZonedDateTime.now();
		
		if(today.getMonth().equals(dateOfBirth.getMonth()) && 
				today.getDayOfMonth() == dateOfBirth.getDayOfMonth()) {
			return true;
		}
		
		return false;
	}

}
