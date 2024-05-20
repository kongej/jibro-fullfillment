package com.jibro.fulfill.dto.product;

import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductReadResponseDto {
	private String productId;
	private String productName;
	private Integer cost;
	private Integer safetyStock;
	private Integer stockCount;	
	private Integer defectiveCount;	
	private String productImage;
	private Company maker;
	private String maker_id;
	
	public ProductReadResponseDto fromProduct(Product product) {
		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.cost = product.getCost();
		this.safetyStock = product.getSafetyStock();
		this.stockCount = product.getStockCount();
		this.defectiveCount = product.getDefectiveCount();
		this.productImage = product.getProductImage();
		this.maker_id = product.getMaker().getCompanyId();
		
		return this;
	}
	
	public static ProductReadResponseDto ProductFactory(Product product) {
		ProductReadResponseDto productReadResponseDto = new ProductReadResponseDto();
		productReadResponseDto.fromProduct(product);
		return productReadResponseDto;
	}	
	
}

