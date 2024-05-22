package com.jibro.fulfill.dto.incoming;

import java.time.LocalDateTime;

import javax.validation.constraints.Positive;

import com.jibro.fulfill.entity.Incoming;
import com.jibro.fulfill.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class IncomingListResponseDto {
	//주문상태
	@NonNull
	private Integer orderStatus;
	//입고번호
	@NonNull
	private String incomingId;
	//입고날짜
	private LocalDateTime updatedAt;
	//제품번호
	@NonNull
	private Product product;
	//수량
	@NonNull
	@Positive
	private Integer incomingCount;
	//총 가격
	@NonNull
	@Positive
	private Integer total;	
	
	public IncomingListResponseDto(Incoming incoming) {
		this.orderStatus = incoming.getOrderStatus();
		this.incomingId = incoming.getIncomingId();
		this.updatedAt = incoming.getUpdatedAt();
		this.product = incoming.getProduct();
		this.incomingCount = incoming.getIncomingCount();
		this.total = incoming.getTotal();
	}
}
