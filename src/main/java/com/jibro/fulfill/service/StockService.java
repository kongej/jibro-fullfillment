package com.jibro.fulfill.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.jibro.fulfill.dto.stock.StockListResponseDto;
import com.jibro.fulfill.dto.stock.StockUpdateResponseDto;

public interface StockService {
	List<StockListResponseDto> stockList() throws Exception;
	void stockUpdate(StockUpdateResponseDto stockUpdateResponseDto) throws NoSuchElementException;
}
