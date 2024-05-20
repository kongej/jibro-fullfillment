package com.jibro.fulfill.dto.product;

import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductModResponseDto {
	private String productId;
	private String productName;
	private Integer cost;
	private Integer safetyStock;
	private Integer stockCount;	
	private Integer defectiveCount;	
	private String productImage;
	private Company maker;	
	private String maker_id;
	
	public ProductModResponseDto fromProduct(Product product) {
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
	
	public static ProductModResponseDto ProductFactory(Product product) {
		ProductModResponseDto productReadResponseDto = new ProductModResponseDto();
		productReadResponseDto.fromProduct(product);
		return productReadResponseDto;
	}
}
