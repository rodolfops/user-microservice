package com.rodolfosaturnino.user.microservice.service;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class DiscountServiceTest {

	@InjectMocks
	private DiscountService discountService;
	

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
}
