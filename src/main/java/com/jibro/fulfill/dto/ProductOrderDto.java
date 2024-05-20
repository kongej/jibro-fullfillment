package com.jibro.fulfill.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductOrderDto {
    /* 입고 번호 */
    private String incoming_id;
    /* 제품 코드 (pk) */
    private String product_id;
    /* 수량 */
    private String incoming_quantity;
}
