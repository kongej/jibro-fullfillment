package com.jibro.fulfill.dto.product;

import com.jibro.fulfill.entity.Company;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ProductInsertDto {
	@NonNull
	private String productId;
	
	@NonNull
	private String productName;
	
	@NonNull
	private Integer cost;
	
	private Integer safetyStock; 
	
	private Integer stockCount;	
	
	private Integer defectiveCount;	
		
	private String productImage;
	
	@NonNull
	private Company maker;	
	
	private String maker_id;
	
}
