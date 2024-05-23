package com.jibro.fulfill.dto.main;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainResponseDto {

	private Integer todayTotalIncomingCount;
	private Integer todayTotalOngoingCount;
	private List<ProductSummaryDto> productList;
	private Integer newOrder;
}
