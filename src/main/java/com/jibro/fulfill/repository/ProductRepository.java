package com.jibro.fulfill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.fulfill.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
