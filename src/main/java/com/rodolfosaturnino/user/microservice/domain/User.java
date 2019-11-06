package com.rodolfosaturnino.user.microservice.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	@Column(name="first_name")
	private String firstName;
	
	@NonNull
	@Column(name="last_name")
	private String lastName;
	
	@NonNull
	@Column(name="date_of_birth")
	private ZonedDateTime dateOfBirth;
	
	
}
