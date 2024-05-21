package com.jibro.fulfill.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

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
	public List<StockListResponseDto> stockList(String productId, Integer page) throws Exception {
		final Integer pageSize = 5;
		
		List<Product> stocks;
		
		if (page == null) {
			page = 0;
		} else {
			page -= 1;
		}
		
		if (productId == null || productId.trim()=="") {
			Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "createdAt");
			stocks = this.productRepository.findAll(pageable).toList();
		}else {
			Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "createdAt");
			Sort sort = Sort.by(Order.desc("createdAt"));
			pageable.getSort().and(sort);
			stocks = this.productRepository.findByProductIdContains(productId, pageable);
		}
		
		return stocks.stream().map(stock-> new StockListResponseDto(stock)).collect(Collectors.toList());
	}

//	@Override
//	public List<StockListResponseDto> stockList(String productId, Integer page) throws Exception {
//		List<Product> stocks;
//		stocks= this.productRepository.findAll();
//		
//		return stocks.stream().map(stock-> new StockListResponseDto(stock)).collect(Collectors.toList());
//	}
	@Override
	public void safetystockUpdate(StockUpdateResponseDto stockUpdateResponseDto) throws NoSuchElementException {
		Product product = this.productRepository.findById(stockUpdateResponseDto.getProductId()).orElseThrow();
		product = stockUpdateResponseDto.safetyStockUpdate(product);
		this.productRepository.save(product);
	}

	@Override
	public void stockUpdate(String productId, Integer count) throws NoSuchElementException {
		Product product = this.productRepository.findById(productId).orElseThrow();
		product.setStockCount(product.getStockCount()-count);
		this.productRepository.save(product);
	}
	
	

}
