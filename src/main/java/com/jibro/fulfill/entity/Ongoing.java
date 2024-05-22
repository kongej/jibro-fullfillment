package com.jibro.fulfill.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "ongoings")
public class Ongoing extends BaseEntity{
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "com.jibro.fulfill.entity.IdGenerator")
	@GeneratedValue(generator = "idGenerator")
	private String ongoingId;			//출고코드
	
	@OneToOne
	@JoinColumn(name = "order_id", nullable = false)
	@ToString.Exclude
	private Order order;				//주문

	@Column(length = 11, nullable = false, unique = true)
	private Integer invc;				//송장번호

	@ManyToOne
	@JoinColumn(name = "delever_id", nullable = false)
	@ToString.Exclude
	private Company delever;			//택배사
	
}
