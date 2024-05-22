package com.jibro.fulfill.dto.order;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderReceiveAPIDto {

	/* 주문코드(pk) */
	private String orderId;

	/* 선택 수량 */
	private int selectedCount;

	/* 구매자명 */
	private String ordererName;

	/* 연락처 */
	private String phoneNumber;

	/* 주소 */
	private String address;

	/* 현 상태 */
	private int status;

	/* 주문 날짜 */
	private LocalDateTime createdAt;

	/* 관련된 제품 코드 */
	private String productId;
	
	/* 셀러 정보 */
	private String seller;
}
