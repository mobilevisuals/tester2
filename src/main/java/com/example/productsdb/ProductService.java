package com.example.productsdb;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

	private ProductRepository repo;

	@Autowired
	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}

	public List<Product> listAll() {
		return repo.findAll();
	}

	@Transactional
	public void save(Product product) {
		repo.save(product);
	}
	
	public Optional<Product> get(long id) {
		return repo.findById(id);
	}

	@Transactional
	public void delete(long id) {
		repo.deleteById(id);
	}
}
