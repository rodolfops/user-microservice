package com.rodolfosaturnino.user.microservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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
		ObjectId id = new ObjectId();
		User user = new User(id.toString(), "TestName", "TestLastName", ZonedDateTime.now());
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
		ObjectId id = new ObjectId();
		User user = new User(id.toString(), "TestName", "TestLastName", ZonedDateTime.now());
		Optional<User> optional = Optional.ofNullable(user);
		given(userRepository.findById(id.toString())).willReturn(optional);
		//when
		User returnedUser = userService.findUser(id.toString());
		//then
		assertThat(returnedUser).isEqualTo(user);
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void findUserTestWithUserNotFound() throws EntityNotFoundException {
		//given
		ObjectId id = new ObjectId();
		Optional<User> optional = Optional.empty();
		given(userRepository.findById(id.toString())).willReturn(optional);
		//when
		userService.findUser(id.toString());
	}
	
	@Test
	public void saveTest() throws ConstraintsViolationException {
		//given
		ObjectId id = new ObjectId();
		ZonedDateTime now = ZonedDateTime.now();
		User userToCreate = new User("TestName", "TestLastName", now);
		User userCreated = new User(id.toString(), "TestName", "TestLastName", now);
		given(userRepository.save(userToCreate)).willReturn(userCreated);
		//when
		User userSaved = userService.save(userToCreate);
		//then
		assertThat(userSaved).isEqualTo(userCreated);
	}
	
	@Test
	public void updateTest() throws EntityNotFoundException {
		//given
		ObjectId id = new ObjectId();
		User user = new User(id.toString(), "TestName", "TestLastName", ZonedDateTime.now());
		Optional<User> optional = Optional.ofNullable(user);
		User userAfterUpdate = new User(id.toString(), "NewTestName", "NewTestLastName", ZonedDateTime.now());
		given(userRepository.findById(id.toString())).willReturn(optional);
		given(userRepository.save(userAfterUpdate)).willReturn(userAfterUpdate);
		//when
		User newUser = userService.update(id.toString(), userAfterUpdate);
		//then
		assertThat(newUser).isEqualTo(userAfterUpdate);
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void deleteTestWithException() throws EntityNotFoundException {
		//given
		ObjectId id = new ObjectId();
		Optional<User> optional = Optional.empty();
		given(userRepository.findById(id.toString())).willReturn(optional);
		//when
		userService.delete(id.toString());
	}
	
}
