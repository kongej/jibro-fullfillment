package com.jibro.fulfill.service;

import com.jibro.fulfill.dto.ProductOrderDto;

public interface IncomingService {
    public String insert(ProductOrderDto productOrderDto) throws Exception;
}
