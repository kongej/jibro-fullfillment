package com.jibro.fulfill.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "companys")
public class Company extends BaseEntity{
	
	@Id
	@GenericGenerator(
			name = "idGenerator", 
			// 전략을 정의한 클래스 Full Path 입력한다 
			strategy = "com.jibro.fulfill.entity.IdGenerator",	
			// UUID란 이름으로 넘길 파라미터 값을 정한다 
			parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "CO")	
		)
	@GeneratedValue(generator = "idGenerator")
	private String companyId;			//거래처코드
		
	private String companyName;			//거래처명
	
	private String companyEmail;		//거래처이메일
	
	private String companyContact;		//거래처연락처

	private String companyCategory;		//거래처카테고리 판매자 :  / 제조사 :  / 택배사 : 
}
