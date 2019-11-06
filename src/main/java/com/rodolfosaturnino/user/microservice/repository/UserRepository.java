package com.rodolfosaturnino.user.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodolfosaturnino.user.microservice.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
