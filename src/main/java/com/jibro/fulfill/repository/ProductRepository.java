package com.jibro.fulfill.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	public List<Product> findByProductNameContains(String productName, Pageable pageable);
	

}
