package com.jibro.fulfill.dto.product;

import com.jibro.fulfill.entity.Company;

import lombok.Getter;

@Getter
public class ProductListResponseDto {
	private String productId;
	private String productName;
	private Integer cost;
	private Integer safetyStock;
	private Integer stockCount;
	private Integer defectiveCount;	
	private String productImage;
	private Company maker;
	private String maker_id;
	
	public ProductListResponseDto(String productId, String productName, Integer cost, 
			Integer safetyStock, Integer stockCount, Integer defectiveCount, 
			String productImage, Company maker, String maker_id) {
		this.productId = productId;
		this.productName = productName;
		this.cost = cost;
		this.safetyStock = safetyStock;
		this.stockCount = stockCount;
		this.defectiveCount = defectiveCount;
		this.productImage = productImage;
		this.maker = maker;
		this.maker_id = maker.getCompanyId();
	}

}
