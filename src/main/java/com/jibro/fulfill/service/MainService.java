package com.jibro.fulfill.service;

import java.util.List;

import com.jibro.fulfill.dto.main.ProductSummaryDto;
import com.jibro.fulfill.dto.sales.SalesSummaryResponseDto;

public interface MainService {

	public Integer getTotalIncomingCountForToday();
	
	public Integer getTotalOngoingCountForToday();
	
	public List<ProductSummaryDto> getProductSummary();
	
	public Integer getNewOrderCount();
	
	public List<SalesSummaryResponseDto> getSalesSummaryList();
}
