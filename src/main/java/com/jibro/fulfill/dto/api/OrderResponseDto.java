package com.jibro.fulfill.dto.api;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author ljy
 * @since 2024.05.20
 * Order response dto 코드
 * 주문 단일 조회 용도
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderResponseDto {
	/* 주문코드(pk) */
	private String orderId;
	
	/* 선택 수량 */
	private int selectedCount;
	
	/* 총 가격 */
	private int totalCost;
	
	/* 구매자명 */
	private String ordererName;
	
	/* 주문 비밀번호 */
	private String orderPassword;
	
	/* 연락처 */
	private String phoneNumber;
	
	/* 주소 */
	private String address;
	
	/* 현 상태 */
	private int status;
	
	/* 송장번호 */
	private int invc;
	
	/* 주문 날짜 */
	private LocalDateTime createdAt;
	
	/* 주문 갱신 날짜 */
	private LocalDateTime updatedAt;
	
	/* 관련된 제품 정보 */
    private String productId;
}
