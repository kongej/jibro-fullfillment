package com.jibro.fulfill.dto.seller;

import com.jibro.fulfill.entity.Company;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SellerModResponseDto {
	private String companyId;
	private String companyName;
	private String companyEmail;
	private String companyContact;
	private String companyCategory;
	
	public SellerModResponseDto fromSeller(Company company) {
		this.companyId = company.getCompanyId();
		this.companyName = company.getCompanyName();
		this.companyEmail = company.getCompanyEmail();
		this.companyContact = company.getCompanyContact();
		this.companyCategory = company.getCompanyCategory();
		return this;
	}
	
	public static SellerModResponseDto SellerFactory(Company company) {
		SellerModResponseDto sellerModResponseDto = new SellerModResponseDto();
		sellerModResponseDto.fromSeller(company);
		return sellerModResponseDto;
	}
}
