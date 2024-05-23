package com.jibro.fulfill.dto.api;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class IncomingApiDto {

    /* 주문번호(pk, yymmdd_001) */
    private String orderId;
    /* 제품번호 */
    private String productId;
    /* 요청수량 */
    private int vendorQuantity;
    /* 주문 거래처명 */
    private String companyName;

}
