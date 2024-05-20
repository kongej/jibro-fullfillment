package com.jibro.fulfill.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "products")
public class Product extends BaseEntity{
	
	@Id
	@Column(length = 50)
	private String productId;			//제품코드
	
	@Column(length = 50, nullable = false)
	private String productName;			//제품명
	
	@Column(length = 50, nullable = false)
	private Integer cost;				//원가
	
	@Column(length = 50, nullable = false, columnDefinition = "int default 0")
	private Integer safetyStock; 		//안전 수량
	
	@Column(length = 50, nullable = false, columnDefinition = "int default 0")
	private Integer stockCount;			//수량
	
	@Column(length = 50, nullable = false, columnDefinition = "int default 0")
	private Integer defectiveCount;		//불량 수량
	
	@Column(length = 255)
	private String productImage;		//제품 이미지
	
	@ManyToOne
	@JoinColumn(name = "maker_id", nullable = false)
	@ToString.Exclude
	private Company maker;				//제조사
	
	public Product() {
        this.safetyStock = 0;
        this.stockCount = 0;
        this.defectiveCount = 0;
    }

    public Product(String productId, String productName, int cost, Company maker) {
        this.productId = productId;
        this.productName = productName;
        this.cost = cost;
        this.safetyStock = 0;
        this.stockCount = 0;
        this.defectiveCount = 0;
        this.maker = maker;
    }
    
    public Product(String productId, String productName, Integer cost, Integer safetyStock, Integer stockCount, Integer defectiveCount, String productImage, Company maker) {
        this.productId = productId;
        this.productName = productName;
        this.cost = cost;
        this.safetyStock = safetyStock;
        this.stockCount = stockCount;
        this.defectiveCount = defectiveCount;
        this.productImage = productImage;
        this.maker = maker;
    }
}
