package com.jibro.fulfill.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {
	
	@Id
	@Column(length = 50)
	private String orderId; // 주문코드
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	@ToString.Exclude
	private Product product; // 제품
	
	@Column(length = 50, nullable = false)
	private String ordererName; // 주문자 성함
	
	@Column(length = 13, nullable = false)
	private String phoneNum; // 핸드폰 번호
	
	@Column(length = 255, nullable = false)
	private String address; // 주소
	
	@Column(length = 50, nullable = false)
	private Integer count; // 수량
	
    @OneToOne(mappedBy = "order")
    @ToString.Exclude
    private Ongoing ongoing;
	
	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	private Integer orderStatus; // 상태 0:상품준비중 1:배송준비중
	
	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	@ToString.Exclude
	private Company seller; // 판매자
	
	@Column(nullable = false)
	private LocalDateTime orderDate; // 주문날짜
	
	@LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedDate;
	
	public Order(String orderId, Product product, String ordererName, String phoneNum, String address, Integer count, Company seller) {
		this.orderId = orderId;
		this.product = product;
		this.ordererName = ordererName;
		this.phoneNum = phoneNum;
		this.address = address;
		this.count = count;
		this.orderStatus = 0; // 기본값 설정
		this.seller = seller;
	}
}
