package com.rodolfosaturnino.user.microservice.domain;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private String id;
	
	@Field(name="first_name")
	private String firstName;
	
	@Field(name="last_name")
	private String lastName;
	
	@Field(name="date_of_birth")
	private ZonedDateTime dateOfBirth;
	
	public User(String firstName, String lastName, ZonedDateTime birthday) {
		this.firstName = firstName;
		this.lastName = lastName;
		dateOfBirth = birthday;
	}
}
