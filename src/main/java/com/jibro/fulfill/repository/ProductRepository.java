package com.jibro.fulfill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
