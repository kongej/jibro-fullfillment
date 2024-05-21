package com.jibro.fulfill.service.impl;

import com.jibro.fulfill.dto.product.ProductStockDto;
import com.jibro.fulfill.entity.Incoming;
import com.jibro.fulfill.repository.IncomingRepository;
import com.jibro.fulfill.repository.StockRepository;
import com.jibro.fulfill.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
/*
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final IncomingRepository incomingRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, IncomingRepository incomingRepository){
        this.stockRepository = stockRepository;
        this.incomingRepository = incomingRepository;
    }
    @Override
    public List<ProductStockDto> allStock(Incoming incoming) throws Exception {
        Long incomingStock = incomingRepository.getTotalQuantityByProduct(incoming);

        return null;
    }
}
*/
