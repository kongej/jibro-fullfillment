package com.jibro.fulfill.dto.vendor;

import lombok.Getter;

@Getter
public class VendorListResponseDto {
	private String companyId;
	private String companyName;
	private String companyEmail;
	private String companyContact;
	private String companyCategory;
	
	public VendorListResponseDto(String companyId, String companyName, String companyEmail, String companyContact, String companyCategory) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.companyContact = companyContact;
		this.companyCategory = companyCategory;
	}
}
