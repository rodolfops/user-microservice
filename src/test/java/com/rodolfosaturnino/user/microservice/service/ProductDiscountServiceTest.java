package com.rodolfosaturnino.user.microservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.ZonedDateTime;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rodolfosaturnino.user.microservice.dataacessobject.DiscountDTO;
import com.rodolfosaturnino.user.microservice.domain.Product;
import com.rodolfosaturnino.user.microservice.domain.User;
import com.rodolfosaturnino.user.microservice.exception.EntityNotFoundException;

public class ProductDiscountServiceTest {

	@InjectMocks
	private ProductDiscountService productDiscountService;
	
	@Mock
	private UserService userService;
	@Mock
	private ProductService productService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getDiscountWithoutPct() throws EntityNotFoundException {
		//given
		ObjectId id = new ObjectId();
		User user = new User(id.toString(), "UserName", "UserLastName", ZonedDateTime.now().minusDays(1));
		given(userService.findUser(id.toString())).willReturn(user);
		ObjectId productId = new ObjectId();
		Product product = new Product(productId.toString(),19000, "product", "product test");
		given(productService.findProduct(productId.toString())).willReturn(product);
		//given(discountService.getDiscount(1l, 1l)).willReturn(0.0);
		//when
		DiscountDTO discount = productDiscountService.calculateDiscount(productId.toString(), id.toString());
		//then
		assertThat(discount.getPct()).isEqualTo(0.0);
	}
	
	@Test
	public void getDiscountBirthday() throws EntityNotFoundException {
		//given
		ObjectId id = new ObjectId();
		User user = new User(id.toString(), "UserName", "UserLastName", ZonedDateTime.now());
		given(userService.findUser(id.toString())).willReturn(user);
		ObjectId productId = new ObjectId();
		Product product = new Product(productId.toString(),19000,"product","product test");
		given(productService.findProduct(productId.toString())).willReturn(product);
		//given(discountService.getDiscount(1l, 1l)).willReturn(0.0);
		//when
		DiscountDTO discount = productDiscountService.calculateDiscount(productId.toString(), id.toString());
		//then
		assertThat(discount.getPct()).isEqualTo(0.05);
	}
	
}
