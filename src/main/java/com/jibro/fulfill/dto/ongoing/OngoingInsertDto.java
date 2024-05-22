package com.jibro.fulfill.dto.ongoing;

import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OngoingInsertDto {
	
	@NonNull
	private Order order;
	
	//지울지도
	@NonNull
	private Integer invc;
	
	@NonNull
	private Company delever;
}
