package com.jibro.fulfill.dto.main;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainResponseDto {

	private Integer todayTotalIncomingCount;
	private Integer todayTotalOngoingCount;
	private Integer stockCount;
	private Integer safetyStock;
	private Integer newOrder;
}
