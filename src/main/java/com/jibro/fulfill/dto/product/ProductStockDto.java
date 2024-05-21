package com.jibro.fulfill.dto.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductStockDto {
    private Long num;
    private String incomingDate;
    private int incomingStock;
    private String ongoingDate;
    private int ongoingStock;
}
