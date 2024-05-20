package com.jibro.fulfill.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
	public List<StockListResponseDto> stockList() throws Exception {
		List<Product> stocks;
		stocks= this.productRepository.findAll();
		
		return stocks.stream().map(stock-> new StockListResponseDto(stock)).collect(Collectors.toList());
	}

	@Override
	public void stockUpdate(StockUpdateResponseDto stockUpdateResponseDto) throws NoSuchElementException {
		Product product = this.productRepository.findById(stockUpdateResponseDto.getProductId()).orElseThrow();
		product = stockUpdateResponseDto.fill(product);
		this.productRepository.save(product);
		System.out.println("완료");
	}

}
