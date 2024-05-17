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
@Table(name = "ongoings")
public class Ongoing extends BaseEntity{
	
	@Id
	private String ongoingId;			//출고코드
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	@ToString.Exclude
	private Order order;				//주문
	
	private Integer invc;				//송장번호
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	@ToString.Exclude
	private Company company;			//택배사

	@ManyToOne
	@JoinColumn(name = "delever_id")
	@ToString.Exclude
	private Company delever;			//택배사
	
}
