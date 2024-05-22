package com.jibro.fulfill.dto.sales;

import lombok.Getter;

@Getter
public class SalesRequestDto {

	private String from;
	private String to;

	public SalesRequestDto(SalesSummaryRequestDto dto) {
		if (dto.getFrom() == null) {
			this.from = "";
		} else {
			this.from = dto.getFrom().toString().substring(0, 10);
		}
		if (dto.getTo() == null) {
			this.to = "";
		} else {
			this.to = dto.getTo().toString().substring(0, 10);
		}
	}

}
