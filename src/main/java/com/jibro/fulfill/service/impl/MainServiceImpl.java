package com.jibro.fulfill.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.main.ProductSummaryDto;
import com.jibro.fulfill.dto.sales.SalesSummaryResponseDto;
import com.jibro.fulfill.repository.IncomingRepository;
import com.jibro.fulfill.repository.OngoingRepository;
import com.jibro.fulfill.repository.OrderRepository;
import com.jibro.fulfill.repository.ProductRepository;
import com.jibro.fulfill.service.MainService;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
    private IncomingRepository incomingRepository;

	@Autowired
	private OngoingRepository ongoingRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Integer getTotalIncomingCountForToday() {
		LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);
        
        return incomingRepository.findTotalIncomingCountForToday(startOfDay, endOfDay);
	}

	@Override
	public Integer getTotalOngoingCountForToday() {
		LocalDate today = LocalDate.now();
		LocalDateTime startOfDay = today.atStartOfDay();
		LocalDateTime endOfDay = today.atTime(LocalTime.MAX);
		
		return ongoingRepository.findTotalOngoingCountForToday(startOfDay, endOfDay);
	}

	@Override
	public List<ProductSummaryDto> getProductSummary() {
		return productRepository.findProductSummaries();
	}

	@Override
	public Integer getNewOrderCount() {
		return orderRepository.countNewOrder();
	}

	@Override
	public List<SalesSummaryResponseDto> getSalesSummaryList() {
		LocalDate today = LocalDate.now();
		
		LocalDateTime fromDate = LocalDateTime.of(today.minusDays(8), LocalTime.MIN);
		LocalDateTime toDate = LocalDateTime.of(today.minusDays(1), LocalTime.MAX);
		List<SalesSummaryResponseDto> salesList = orderRepository.findOrderSummariesEmailContent(fromDate, toDate);

		return salesList;
	}

}
