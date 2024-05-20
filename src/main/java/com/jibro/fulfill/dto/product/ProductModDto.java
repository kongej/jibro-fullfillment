package com.jibro.fulfill.dto.product;

import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Product;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ProductModDto {
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
	
	public Product fill(Product product) {
		product.setProductId(this.productId);
		product.setProductName(this.productName);
		product.setCost(this.cost);
		product.setSafetyStock(this.safetyStock);
		product.setStockCount(this.stockCount);
		product.setDefectiveCount(this.defectiveCount);
		product.setProductImage(this.productImage);
		product.setMaker(this.maker);
		
		return product;
	}
}
