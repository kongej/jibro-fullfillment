package com.jibro.fulfill.dto.vendor;

import com.jibro.fulfill.entity.Company;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class VendorModDto {
	@NonNull
	private String companyId;
	@NonNull
	private String companyName;
	@NonNull
	private String companyEmail;
	private String companyContact;
	@NonNull
	private String companyCategory;
	
	public Company fill(Company company) {
		company.setCompanyId(this.companyId);
		company.setCompanyName(this.companyName);
		company.setCompanyEmail(this.companyEmail);
		company.setCompanyContact(this.companyContact);
		company.setCompanyCategory(this.companyCategory);
		return company;
	}
}
