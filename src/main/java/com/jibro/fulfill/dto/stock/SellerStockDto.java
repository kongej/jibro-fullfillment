package com.jibro.fulfill.dto.stock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerStockDto {
    /* 제품코드(pk) */
    private String productId;

    /* 수량 */
    private Integer productCount;
}
