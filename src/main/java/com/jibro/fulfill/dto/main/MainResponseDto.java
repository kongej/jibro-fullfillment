package com.jibro.fulfill.dto.main;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainResponseDto {

	private Integer todayIncomingTotalCount;
	private Integer todayOngoingTotalCount;
	private String warningStockProduct;
	private Integer newOrder;
}
