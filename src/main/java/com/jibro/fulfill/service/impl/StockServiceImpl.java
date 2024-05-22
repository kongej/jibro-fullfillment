package com.jibro.fulfill.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.stock.StockListPageDto;
import com.jibro.fulfill.dto.stock.StockListResponseDto;
import com.jibro.fulfill.dto.stock.StockUpdateResponseDto;
import com.jibro.fulfill.entity.Product;
import com.jibro.fulfill.repository.ProductRepository;
import com.jibro.fulfill.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	private ProductRepository productRepository;
	
	public StockServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public StockListPageDto stockList(String searchId, Integer page) throws Exception {
		final Integer pageSize = 10;
		
		page -= 1;
		
		Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "createdAt");
		Page<Product> stockPage;
		
		if (searchId == null || searchId.trim() == "") {
			stockPage = this.productRepository.findAll(pageable);
		}else {
			Sort sort = Sort.by(Order.desc("createdAt"));
			pageable.getSort().and(sort);
			stockPage = this.productRepository.findByProductIdContains(searchId, pageable);
		}
		List<Product> stocks = stockPage.getContent();
		
		List<StockListResponseDto> response = stocks.stream().map(stock-> new StockListResponseDto(stock)).collect(Collectors.toList());
		
		return new StockListPageDto(response, stockPage.isLast(), stockPage.getTotalPages());
	}

	@Override
	public void safetystockUpdate(StockUpdateResponseDto stockUpdateResponseDto) throws NoSuchElementException {
		Product product = this.productRepository.findById(stockUpdateResponseDto.getProductId()).orElseThrow();
		product = stockUpdateResponseDto.safetyStockUpdate(product);
		this.productRepository.save(product);
	}

	@Override
	public void stockUpdate(String searchId, Integer count) throws NoSuchElementException {
		Product product = this.productRepository.findById(searchId).orElseThrow();
		product.setStockCount(product.getStockCount()-count);
		this.productRepository.save(product);
	}
	
	

}
