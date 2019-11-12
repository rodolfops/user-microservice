package com.rodolfosaturnino.user.microservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rodolfosaturnino.user.microservice.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

}
