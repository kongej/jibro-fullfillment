package com.jibro.fulfill.dto.seller;

import lombok.Getter;

@Getter
public class SellerListResponseDto {
	
	private String companyId;
	private String companyName;
	private String companyEmail;
	private String companyContact;
	private String companyCategory;
	
	public SellerListResponseDto(String companyId, String companyName, String companyEmail, String companyContact, String companyCategory) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.companyContact = companyContact;
		this.companyCategory = companyCategory;
	}
	
}
