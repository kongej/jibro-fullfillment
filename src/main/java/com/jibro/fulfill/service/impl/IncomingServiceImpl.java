package com.jibro.fulfill.service.impl;

import com.jibro.fulfill.dto.ProductOrderDto;
import com.jibro.fulfill.entity.Incoming;
import com.jibro.fulfill.entity.Product;
import com.jibro.fulfill.repository.IncomingRepository;
import com.jibro.fulfill.repository.ProductRepository;
import com.jibro.fulfill.service.IncomingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomingServiceImpl implements IncomingService {
    private IncomingRepository incomingRepository;
    private ProductRepository productRepository;

    @Autowired
    public IncomingServiceImpl(IncomingRepository incomingRepository, ProductRepository productRepository){
        this.incomingRepository = incomingRepository;
        this.productRepository = productRepository;
    }
    @Override
    public ProductOrderDto insert(ProductOrderDto productOrderDto) throws Exception {
        Product product = productRepository.findById(productOrderDto.getProduct_id())
                .orElseThrow(() -> new Exception("product_id 확인 불가"));

        Incoming incoming = Incoming.builder()
                .product(product)
                .incomingCount(Integer.parseInt(productOrderDto.getIncoming_quantity()))
                .build();
        this.incomingRepository.save(incoming);

        return productOrderDto;
    }
}
