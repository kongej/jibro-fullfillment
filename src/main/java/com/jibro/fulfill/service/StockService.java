package com.jibro.fulfill.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.jibro.fulfill.dto.stock.StockListResponseDto;
import com.jibro.fulfill.dto.stock.StockUpdateResponseDto;

public interface StockService {
	List<StockListResponseDto> stockList(String productId, Integer page) throws Exception;
	void safetystockUpdate(StockUpdateResponseDto stockUpdateResponseDto) throws NoSuchElementException;
	void stockUpdate(String orderId) throws NoSuchElementException;
}
