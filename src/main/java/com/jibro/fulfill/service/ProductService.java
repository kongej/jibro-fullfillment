package com.jibro.fulfill.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.jibro.fulfill.dto.product.ProductInsertDto;
import com.jibro.fulfill.dto.product.ProductListResponseDto;
import com.jibro.fulfill.dto.product.ProductModDto;
import com.jibro.fulfill.dto.product.ProductModResponseDto;
import com.jibro.fulfill.dto.product.ProductReadResponseDto;

public interface ProductService {
	List<ProductListResponseDto> productList(String productName, Integer page);
	String insert(ProductInsertDto productInsertDto);
	ProductReadResponseDto read(String productId) throws NoSuchElementException;
	ProductModResponseDto edit(String productId) throws NoSuchElementException;
	void update(ProductModDto productModDto) throws NoSuchElementException;
	void delete(String productId) throws NoSuchElementException;
}
