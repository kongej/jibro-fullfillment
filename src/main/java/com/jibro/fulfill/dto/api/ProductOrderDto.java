package com.jibro.fulfill.dto.api;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductOrderDto {
    /* 주문번호(pk, yymmdd_001) */
    private String orderId;

    /* 제품 코드 (pk) */
    private String productId;
    /* 수량 */
    private String vendorQuantity;
    /* 거래처명(풀필먼트) */
    /* 풀필먼트 이름 */
    private String companyName;
}
