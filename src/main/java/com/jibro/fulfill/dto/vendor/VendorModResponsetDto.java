package com.jibro.fulfill.dto.vendor;

import com.jibro.fulfill.entity.Company;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class VendorModResponsetDto {
	private String companyId;
	private String companyName;
	private String companyEmail;
	private String companyContact;
	private String companyCategory;
	
	public VendorModResponsetDto fromVendor(Company company) {
		this.companyId = company.getCompanyId();
		this.companyName = company.getCompanyName();
		this.companyEmail = company.getCompanyEmail();
		this.companyContact = company.getCompanyContact();
		this.companyCategory = company.getCompanyCategory();
		return this;
	}
	
	public static VendorModResponsetDto vendorFactory(Company company) {
		VendorModResponsetDto vendorModResponsetDto = new VendorModResponsetDto();
		vendorModResponsetDto.fromVendor(company);
		return vendorModResponsetDto;
	}
}
