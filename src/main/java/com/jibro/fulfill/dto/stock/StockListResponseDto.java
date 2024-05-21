package com.jibro.fulfill.dto.stock;

import javax.validation.constraints.Positive;

import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockListResponseDto {
	//제품번호
	@NonNull
	private String productId;	
	//제품명
	@NonNull
	private String productName;	
	//가격
	@NonNull
	@Positive
	private Integer cost;
	//수량
	@NonNull
	@Positive
	private Integer stockCount;
	//안전수량
	@Positive
	private Integer safetyStock;
	//거래처 코드
	@NonNull
	private Company maker;
	
	public StockListResponseDto(Product product) {
		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.cost = product.getCost();
		this.stockCount = product.getStockCount();
		this.safetyStock = product.getSafetyStock();
		this.maker = product.getMaker();
	}
}
