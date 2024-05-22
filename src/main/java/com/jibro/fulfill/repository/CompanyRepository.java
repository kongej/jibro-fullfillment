package com.jibro.fulfill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
	// 판매자 목록 : S로 시작되는 목록만
	 List<Company> findByCompanyCategoryAndDelYN(String companyCategory, String delYN);
	 
	// 거래처목록 : S를 제외한 목록만 
	 List<Company> findByCompanyCategoryNotAndDelYN(String companyCategory, String delYN);
	 
	 // 이메일 목록 : Maker, Seller만
	 @Query("SELECT c.companyEmail FROM Company c WHERE c.companyCategory IN ('M', 'S') AND c.delYN = 'N'")
	 List<String> findCompanyEmailsByCategory();
}
