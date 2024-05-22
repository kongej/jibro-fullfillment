package com.jibro.fulfill.dto.stock;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockListPageDto {
	private List<StockListResponseDto> stocks;
	private boolean lastPage;
	private int totalPage;
	
	public StockListPageDto(List<StockListResponseDto> stocks,
							boolean lastPage,
							int totalPage) {
		this.stocks = stocks;
		this.lastPage = lastPage;
		this.totalPage = totalPage;
	}
}