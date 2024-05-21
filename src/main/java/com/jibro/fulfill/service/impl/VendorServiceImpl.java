package com.jibro.fulfill.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.vendor.VendorInsertDto;
import com.jibro.fulfill.dto.vendor.VendorListResponseDto;
import com.jibro.fulfill.dto.vendor.VendorModDto;
import com.jibro.fulfill.dto.vendor.VendorModResponsetDto;
import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.repository.CompanyRepository;
import com.jibro.fulfill.service.VendorService;

@Service
public class VendorServiceImpl implements VendorService{
	
	private CompanyRepository companyRepository;
	
	public VendorServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	// 거래처 목록화면
	@Override
	public List<VendorListResponseDto> vendorList() {
		List<Company> vendors = companyRepository.findByCompanyCategoryNot("S");
		
		return vendors.stream().map(company -> new VendorListResponseDto(
				company.getCompanyId(), company.getCompanyName(), 
				company.getCompanyEmail(), company.getCompanyContact(), 
				company.getCompanyCategory())).collect(Collectors.toList());
	}
		
	// 거래처 등록
	@Override
	public String insert(VendorInsertDto vendorInsertDto) {
		Company company = Company.builder()
				.companyId(vendorInsertDto.getCompanyId())
				.companyName(vendorInsertDto.getCompanyName())
				.companyEmail(vendorInsertDto.getCompanyEmail())
				.companyContact(vendorInsertDto.getCompanyContact())
				.companyCategory(vendorInsertDto.getCompanyCategory())
				.build();
		this.companyRepository.save(company);
		return company.getCompanyId();
	}
	
	// 거래처 수정화면 
	@Override
	public VendorModResponsetDto edit(String companyId) throws NoSuchElementException {
		Company company = this.companyRepository.findById(companyId).orElseThrow();
		VendorModResponsetDto vendorModResponsetDto = new VendorModResponsetDto();
		vendorModResponsetDto.fromVendor(company);
		
		return VendorModResponsetDto.vendorFactory(company);
	}
	
	// 거래처 수정 기능
	@Override
	public void update(VendorModDto vendorModDto) throws NoSuchElementException {
		Company company = this.companyRepository.findById(vendorModDto.getCompanyId()).orElseThrow();
		company = vendorModDto.fill(company);
		this.companyRepository.save(company);
	}
	
	// 거래처 삭제기능
	public void delete(String companyId) throws NoSuchElementException {
		Company company = this.companyRepository.findById(companyId).orElseThrow();
		this.companyRepository.delete(company);
	}

}
