package com.jibro.fulfill.entity;

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
	@GenericGenerator(
			name = "idGenerator", 
			// 전략을 정의한 클래스 Full Path 입력한다 
			strategy = "com.jibro.fulfill.entity.IdGenerator"
			// UUID란 이름으로 넘길 파라미터 값을 정한다 	
		)
	@GeneratedValue(generator = "idGenerator")
	private String incomingId;				//입고코드
	
	private Integer incomingCount;			//입고수량
	
	private Integer total;					//총가격	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@ToString.Exclude
	private Product product;				//제품
}
