package com.jibro.fulfill.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "products")
public class Product extends BaseEntity{
	
	@Id
	private String productId;			//제품코드
		
	private String productName;			//제품명
	
	private Integer cost;				//원가
	
	private Integer safetyStock; 		//안전 수량

	private Integer stockCount;			//수량
	
	private Integer defectiveCount;		//불량 수량

	private String productImage;		//제품 이미지
}
