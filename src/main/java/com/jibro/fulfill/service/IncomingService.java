package com.jibro.fulfill.service;

import com.jibro.fulfill.dto.ProductOrderDto;

public interface IncomingService {
    public ProductOrderDto insert(ProductOrderDto productOrderDto) throws Exception;
}
