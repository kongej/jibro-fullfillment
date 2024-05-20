package com.jibro.fulfill.dto.seller;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SellerInsertDto {
	@NonNull
	private String companyId;
	@NonNull
	private String companyName;
	@NonNull
	private String companyEmail;
	private String companyContact;
	private String companyCategory;
	
//	public SellerInsertDto(String companyId, String companyName, String companyEmail, String companyContact, String companyCategory) {
//		this.companyId = companyId;
//		this.companyName = companyName;
//		this.companyEmail = companyEmail;
//		this.companyContact = companyContact;
//		this.companyCategory = companyCategory;
//	}
}
