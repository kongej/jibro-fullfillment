package com.jibro.fulfill.dto.vendor;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class VendorInsertDto {
	@NonNull
	private String companyId;
	@NonNull
	private String companyName;
	@NonNull
	private String companyEmail;
	private String companyContact;
	private String companyCategory;
}
