package com.jibro.fulfill.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.seller.SellerInsertDto;
import com.jibro.fulfill.dto.seller.SellerListResponseDto;
import com.jibro.fulfill.dto.seller.SellerModDto;
import com.jibro.fulfill.dto.seller.SellerModResponseDto;
import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.repository.CompanyRepository;
import com.jibro.fulfill.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
	
	private CompanyRepository companyRepository;
	
	public SellerServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	// 판매자 목록화면
	@Override
	public List<SellerListResponseDto> sellerList(){
		List<Company> sellers = companyRepository.findByCompanyCategory("S");
		
		//String companyId, String companyName, String companyEmail, String companyContact, 
		//String companyCategory
		return sellers.stream().map(company -> new SellerListResponseDto(
				company.getCompanyId(), company.getCompanyName(), 
				company.getCompanyEmail(), company.getCompanyContact(), 
				company.getCompanyCategory())).collect(Collectors.toList());		
	}
	
	// 판매자 추가
	@Override
	public String insert(SellerInsertDto sellerInsertDto) {
		Company company = Company.builder()
				.companyId(sellerInsertDto.getCompanyId())
				.companyName(sellerInsertDto.getCompanyName())
				.companyEmail(sellerInsertDto.getCompanyEmail())
				.companyContact(sellerInsertDto.getCompanyContact())
				.companyCategory(sellerInsertDto.getCompanyCategory())
				.build();
		
		this.companyRepository.save(company);
		
		return company.getCompanyId();
	}
	
	// 판매자 수정화면
	@Override
	public SellerModResponseDto edit(String companyId) throws NoSuchElementException {
		Company company = this.companyRepository.findById(companyId).orElseThrow();
		SellerModResponseDto sellerModResponseDto = new SellerModResponseDto();
		sellerModResponseDto.fromSeller(company);
		
		return SellerModResponseDto.SellerFactory(company);
	}
	
	// 판매자 수정 기능
	@Override
	public void update(SellerModDto sellerModDto) throws NoSuchElementException {
		Company company = this.companyRepository.findById(sellerModDto.getCompanyId()).orElseThrow();
		company = sellerModDto.fill(company);
		this.companyRepository.save(company);
	}
	
	// 판매자 삭제 기능
	@Override
	public void delete(String companyId) throws NoSuchElementException {
		Company company = this.companyRepository.findById(companyId).orElseThrow();
		this.companyRepository.delete(company);
	}
	
}
