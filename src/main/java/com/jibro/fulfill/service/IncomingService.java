package com.jibro.fulfill.service;

import com.jibro.fulfill.dto.ProductOrderDto;

public interface IncomingService {
    public void insert(ProductOrderDto productOrderDto) throws Exception;
}
