package com.jibro.fulfill.service;

import java.util.NoSuchElementException;

import com.jibro.fulfill.dto.stock.StockListPageDto;
import com.jibro.fulfill.dto.stock.StockUpdateResponseDto;

public interface StockService {
	StockListPageDto stockList(String searchId, Integer page) throws Exception;
	void safetystockUpdate(StockUpdateResponseDto stockUpdateResponseDto) throws NoSuchElementException;
	void stockUpdate(String searchId, Integer count) throws NoSuchElementException;
}
