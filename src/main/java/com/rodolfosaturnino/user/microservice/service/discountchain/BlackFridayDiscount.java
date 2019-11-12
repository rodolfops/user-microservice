package com.rodolfosaturnino.user.microservice.service.discountchain;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZonedDateTime;

import com.rodolfosaturnino.user.microservice.dataacessobject.DiscountDTO;
import com.rodolfosaturnino.user.microservice.domain.Product;
import com.rodolfosaturnino.user.microservice.domain.User;

public class BlackFridayDiscount extends DiscountHandler {

	private static double BLACK_FRIDAY_DISCOUNT = 0.10;
	private static int LAST_DAY_OF_NOVEMBER = 30;

	@Override
	public DiscountDTO getDiscount(Product product, User user) {
		if (isBlackFriday()) {
			int valueInCents = (int) Math.round(product.getPriceInCents() * BLACK_FRIDAY_DISCOUNT);
			return new DiscountDTO(BLACK_FRIDAY_DISCOUNT, new Integer(valueInCents));
		} else {
			return next.getDiscount(product, user);
		}
	}

	private boolean isBlackFriday() {
		ZonedDateTime today = ZonedDateTime.now();

		if (LAST_DAY_OF_NOVEMBER - today.getDayOfMonth() < 7
				&& today.getDayOfWeek().equals(DayOfWeek.FRIDAY)
				&& today.getMonth().equals(Month.NOVEMBER)) {
			return true;
		}

		return false;
	}

}
