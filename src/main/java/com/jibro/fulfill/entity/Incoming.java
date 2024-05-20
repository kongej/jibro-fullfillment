package com.jibro.fulfill.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
@Table(name = "incomings")
public class Incoming extends BaseEntity{
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "com.jibro.fulfill.entity.IdGenerator")
	@GeneratedValue(generator = "idGenerator")
	private String incomingId;				//입고코드
	
	@Column(length = 50, nullable = false)
	private Integer incomingCount;			//입고수량
	
	@Column(length = 50, nullable = false)
	private Integer total;					//총가격	
	
	@Column(length = 1, nullable = false, columnDefinition = "int default 1")
	private Integer orderStatus;		//상태 1:입고 대기 2:입고 완료
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@ToString.Exclude
	private Product product;				//제품
	
}
