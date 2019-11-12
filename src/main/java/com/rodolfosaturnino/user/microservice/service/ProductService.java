package com.rodolfosaturnino.user.microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rodolfosaturnino.user.microservice.domain.Product;
import com.rodolfosaturnino.user.microservice.exception.EntityNotFoundException;
import com.rodolfosaturnino.user.microservice.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	
	@Autowired
	public ProductService(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional
	public Product findProduct(String id) throws EntityNotFoundException {
		return productRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(
						"Could not find entity with id: " + id));
	}

	@Transactional
	public List<Product> findAll(Pageable pageable) {
		Page<Product> page = productRepository.findAll(pageable);
		if (page.hasContent()) {
			return page.getContent();
		}
		return new ArrayList<Product>();
	}
}
