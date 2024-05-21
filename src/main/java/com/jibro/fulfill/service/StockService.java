package com.jibro.fulfill.service;

import com.jibro.fulfill.dto.product.ProductStockDto;
import com.jibro.fulfill.entity.Incoming;

import java.util.List;

public interface StockService {
   public List<ProductStockDto> allStock(Incoming incoming) throws Exception;
}
