package com.rodolfosaturnino.user.microservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.ZonedDateTime;

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
		User user = new User("5dc99d7f0ec11a53f8961192", "Rodolfo", "Saturnino", ZonedDateTime.now().minusDays(1));
		given(userService.findUser("5dc99d7f0ec11a53f8961192")).willReturn(user);
		Product product = new Product("5dc99d7f0ec11a53f8961192",19000,"product","product test");
		given(productService.findProduct("5dc99d7f0ec11a53f8961192")).willReturn(product);
		//given(discountService.getDiscount(1l, 1l)).willReturn(0.0);
		//when
		DiscountDTO discount = productDiscountService.calculateDiscount("5dc99d7f0ec11a53f8961192", "5dc99d7f0ec11a53f8961192");
		//then
		assertThat(discount.getPct()).isEqualTo(0.0);
	}
	
	@Test
	public void getDiscountBirthday() throws EntityNotFoundException {
		//given
		User user = new User("5dc99d7f0ec11a53f8961192", "Rodolfo", "Saturnino", ZonedDateTime.now());
		given(userService.findUser("5dc99d7f0ec11a53f8961192")).willReturn(user);
		Product product = new Product("5dc99d7f0ec11a53f8961192",19000,"product","product test");
		given(productService.findProduct("5dc99d7f0ec11a53f8961192")).willReturn(product);
		//given(discountService.getDiscount(1l, 1l)).willReturn(0.0);
		//when
		DiscountDTO discount = productDiscountService.calculateDiscount("5dc99d7f0ec11a53f8961192", "5dc99d7f0ec11a53f8961192");
		//then
		assertThat(discount.getPct()).isEqualTo(0.05);
	}
	
	/*@Test
	public void getDiscountBlackFriday() throws EntityNotFoundException {
		//given
		User user = new User(1l, "Rodolfo", "Saturnino", ZonedDateTime.now());
		Optional<User> optional = Optional.ofNullable(user);
		given(userRepository.findById(1l)).willReturn(optional);
		//ProductDTO product = new ProductDTO("1",19000,"product","product test",new DiscountDTO());
		//given(discountService.getProductWithGrpc(1l)).willReturn(product);
		//given(discountService.getDiscount(1l, 1l)).willReturn(0.0);
		//when
		double discount = discountService.getDiscount(1l, 1l);
		//then
		assertThat(discount).isEqualTo(0.0);
	}*/
	
}
