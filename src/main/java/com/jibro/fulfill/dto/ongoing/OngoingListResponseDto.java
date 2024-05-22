package com.jibro.fulfill.dto.ongoing;

import java.time.LocalDateTime;

import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Ongoing;
import com.jibro.fulfill.entity.Order;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class OngoingListResponseDto {

	//출고번호
	@NonNull
	private String ongoingId;	
	//출고날짜
	private LocalDateTime createdAt;
	//Order 주문번호
	@NonNull
	private Order order;
	//송장번호
	@NonNull
	private String invc;
	//Order 제품번호
	//Order 수량
	//택배사
	@NonNull
	private Company delever;
	
	public OngoingListResponseDto(Ongoing ongoing) {
		this.ongoingId = ongoing.getOngoingId();
		this.createdAt = ongoing.getCreatedAt();
		this.order = ongoing.getOrder();
		this.invc = ongoing.getInvc();
		this.delever = ongoing.getDelever();
	}
}
