package com.jibro.fulfill.dto.stock;

import javax.validation.constraints.Positive;

import com.jibro.fulfill.entity.Product;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class StockUpdateResponseDto {
	@NonNull
	private String productId;	
	
	@Positive
	private Integer safetyStock;
	
	public Product safetyStockUpdate(Product product) {
		product.setSafetyStock(this.safetyStock);
		return product;
	}

}
