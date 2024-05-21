package com.jibro.fulfill.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	
	@Column(length = 50, nullable = true)
	private Integer total;					//총가격

	@ManyToOne
	@JoinColumn(name = "product_id")
	@ToString.Exclude
	private Product product;				//제품
	
}
