package com.jibro.fulfill.dto.order;

import java.time.LocalDateTime;

import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Ongoing;
import com.jibro.fulfill.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderListResponseDto {

	private String orderId; 			// 주문코드
	private Product product; 			// 제품
//	private String productId;			// 제품코드
	private String ordererName; 		// 주문자 성함
	private String phoneNum; 			// 핸드폰 번호
	private String address; 			// 주소
	private Integer count; 				// 수량
    private Ongoing ongoing;			// 출고
//    private Integer invc;				// 송장번호
	private Integer orderStatus; 		// 상태 0:상품준비중 1:배송준비중
	private Company seller; 			// 판매자
//	private String sellerId;			// 판매자코드
	private LocalDateTime orderDate; 	// 주문날짜
	
}
