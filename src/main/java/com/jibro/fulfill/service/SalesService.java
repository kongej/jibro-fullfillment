package com.jibro.fulfill.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.jibro.fulfill.dto.sales.SalesSummaryRequestDto;
import com.jibro.fulfill.dto.sales.SalesSummaryResponseDto;

public interface SalesService {

	public Page<SalesSummaryResponseDto> getOrderSummaries(SalesSummaryRequestDto dto, int page, int size);

	public void sendEmailClick(String email, LocalDateTime from, LocalDateTime to);

}
