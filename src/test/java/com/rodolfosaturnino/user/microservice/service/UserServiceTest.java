package com.rodolfosaturnino.user.microservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.rodolfosaturnino.user.microservice.domain.User;
import com.rodolfosaturnino.user.microservice.exception.ConstraintsViolationException;
import com.rodolfosaturnino.user.microservice.exception.EntityNotFoundException;
import com.rodolfosaturnino.user.microservice.repository.UserRepository;

public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findAllTest() {
		//given
		User user = new User("5dc99d7f0ec11a53f8961192", "Rodolfo", "Saturnino", ZonedDateTime.now());
		List<User> userList = new ArrayList<>();
		Collections.addAll(userList, user);
		Pageable pageable = PageRequest.of(0, 20);
		Page<User> pages = new PageImpl<User>(userList, pageable, userList.size());
		given(userRepository.findAll(pageable)).willReturn(pages);
		//when
		List<User> returnUserList = userService.findAll(pageable);
		//then
		assertThat(returnUserList).isEqualTo(userList);
	}
	
	@Test
	public void findUserTest() throws EntityNotFoundException {
		//given
		User user = new User("5dc99d7f0ec11a53f8961192", "Rodolfo", "Saturnino", ZonedDateTime.now());
		Optional<User> optional = Optional.ofNullable(user);
		given(userRepository.findById("5dc99d7f0ec11a53f8961192")).willReturn(optional);
		//when
		User returnedUser = userService.findUser("5dc99d7f0ec11a53f8961192");
		//then
		assertThat(returnedUser).isEqualTo(user);
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void findUserTestWithUserNotFound() throws EntityNotFoundException {
		//given
		Optional<User> optional = Optional.empty();
		given(userRepository.findById("5dc99d7f0ec11a53f8961192")).willReturn(optional);
		//when
		userService.findUser("5dc99d7f0ec11a53f8961192");
	}
	
	@Test
	public void saveTest() throws ConstraintsViolationException {
		//given
		ZonedDateTime now = ZonedDateTime.now();
		User userToCreate = new User("Rodolfo", "Saturnino", now);
		User userCreated = new User("5dc99d7f0ec11a53f8961192", "Rodolfo", "Saturnino", now);
		given(userRepository.save(userToCreate)).willReturn(userCreated);
		//when
		User userSaved = userService.save(userToCreate);
		//then
		assertThat(userSaved).isEqualTo(userCreated);
	}
	
	@Test
	public void updateTest() throws EntityNotFoundException {
		//given
		User user = new User("5dc99d7f0ec11a53f8961192", "Rodolfo", "Pereira", ZonedDateTime.now());
		Optional<User> optional = Optional.ofNullable(user);
		User userAfterUpdate = new User("5dc99d7f0ec11a53f8961192", "Rodolfo", "Saturnino", ZonedDateTime.now());
		given(userRepository.findById("5dc99d7f0ec11a53f8961192")).willReturn(optional);
		given(userRepository.save(userAfterUpdate)).willReturn(userAfterUpdate);
		//when
		User newUser = userService.update("5dc99d7f0ec11a53f8961192", userAfterUpdate);
		//then
		assertThat(newUser).isEqualTo(userAfterUpdate);
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void deleteTestWithException() throws EntityNotFoundException {
		//given
		Optional<User> optional = Optional.empty();
		given(userRepository.findById("5dc99d7f0ec11a53f8961192")).willReturn(optional);
		//when
		userService.delete("5dc99d7f0ec11a53f8961192");
	}
	
}
