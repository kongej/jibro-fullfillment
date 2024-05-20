package com.jibro.fulfill.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.jibro.fulfill.dto.vendor.VendorInsertDto;
import com.jibro.fulfill.dto.vendor.VendorListResponseDto;
import com.jibro.fulfill.dto.vendor.VendorModDto;
import com.jibro.fulfill.dto.vendor.VendorModResponsetDto;

public interface VendorService {
	List<VendorListResponseDto> vendorList();
	String insert(VendorInsertDto vendorInsertDto);
	VendorModResponsetDto edit(String companyId) throws NoSuchElementException;
	void update(VendorModDto vendorModDto) throws NoSuchElementException;
	void delete(String companyId) throws NoSuchElementException; 
}
