package com.jibro.fulfill.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "orders")
public class Order extends BaseEntity{
	
	@Id
	private String orderId;				//주문코드
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	@ToString.Exclude
	private Product product;			//제품
	
	private String ordererName;			//주문자 성함
	
	private String phoneNum;			//핸드폰 번호
	
	private String address;				//주소
	
	private Integer count;				//수량
	
	private Integer orderStatus;		//상태 0:상품준비중 1:배송준비중

	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	@ToString.Exclude
	private Company seller;			//판매자
	
}
