package com.jibro.fulfill.dto.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductSummaryDto {

    private String productId;
    private Integer stockCount;
    private Integer safetyStock;

}
