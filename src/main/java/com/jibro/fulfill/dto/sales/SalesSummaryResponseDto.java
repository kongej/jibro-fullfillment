package com.jibro.fulfill.dto.sales;

import java.time.LocalDate;

import com.jibro.fulfill.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SalesSummaryResponseDto {
	
	private Product product;
	
	private LocalDate orderDate;
	
	private int totalCount;

}
