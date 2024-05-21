package com.jibro.fulfill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
	// 판매자 목록 : S로 시작되는 목록만
	 List<Company> findByCompanyCategory(String companyCategory);
	 
	// 거래처목록 : S를 제외한 목록만 
	 List<Company> findByCompanyCategoryNot(String companyCategory);

}
