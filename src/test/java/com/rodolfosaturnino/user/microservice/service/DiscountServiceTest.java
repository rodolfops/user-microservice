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
import com.rodolfosaturnino.user.microservice.domain.User;
import com.rodolfosaturnino.user.microservice.exception.EntityNotFoundException;

public class DiscountServiceTest {

	@InjectMocks
	private DiscountService discountService;
	
	@Mock
	private UserService userService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getDiscountWithoutPct() throws EntityNotFoundException {
		//given
		User user = new User(1l, "Rodolfo", "Saturnino", ZonedDateTime.now().minusDays(1));
		given(userService.findUser(1l)).willReturn(user);
		//ProductDTO product = new ProductDTO("1",19000,"product","product test",new DiscountDTO());
		//given(discountService.getProductWithGrpc(1l)).willReturn(product);
		//given(discountService.getDiscount(1l, 1l)).willReturn(0.0);
		//when
		DiscountDTO discount = discountService.calculateDiscount(1l, 1l);
		//then
		assertThat(discount.getPct()).isEqualTo(0.0);
	}
	
	@Test
	public void getDiscountBirthday() throws EntityNotFoundException {
		//given
		User user = new User(1l, "Rodolfo", "Saturnino", ZonedDateTime.now());
		given(userService.findUser(1l)).willReturn(user);
		//ProductDTO product = new ProductDTO("1",19000,"product","product test",new DiscountDTO());
		//given(discountService.getProductWithGrpc(1l)).willReturn(product);
		//given(discountService.getDiscount(1l, 1l)).willReturn(0.0);
		//when
		DiscountDTO discount = discountService.calculateDiscount(1l, 1l);
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
