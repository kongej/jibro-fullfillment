package com.jibro.fulfill.service;

import java.util.List;

import com.jibro.fulfill.dto.main.ProductSummaryDto;

public interface MainService {

	public Integer getTotalIncomingCountForToday();
	
	public Integer getTotalOngoingCountForToday();
	
	public List<ProductSummaryDto> getProductSummary();
	
	public Integer getNewOrderCount();
}
