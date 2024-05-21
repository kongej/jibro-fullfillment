package com.jibro.fulfill.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "companys")
public class Company extends BaseEntity{
	
	@Id
	@Column(length = 50)
	private String companyId;			//거래처코드
	
	@Column(length = 50, nullable = false)
	private String companyName;			//거래처명
	
	@Column(length = 50, nullable = false)
	private String companyEmail;		//거래처이메일
	
	@Column(length = 50, nullable = false)
	private String companyContact;		//거래처연락처

	@Column(length = 1, nullable = false)
	private String companyCategory;		//거래처카테고리 판매자 : S / 제조사 : M / 택배사 : D

	public Company(String companyId, String companyName, String companyEmail, String companyContact, String companyCategory) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.companyContact = companyContact;
		this.companyCategory = companyCategory;
	}
}
