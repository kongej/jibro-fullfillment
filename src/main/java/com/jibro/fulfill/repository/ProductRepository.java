package com.jibro.fulfill.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.dto.main.ProductSummaryDto;
import com.jibro.fulfill.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	// 상품 목록 화면
	public List<Product> findByProductNameContains(String productName, Pageable pageable);
	
	//재고 목록 화면
	public Page<Product> findAll(Pageable pageable);
	public Page<Product> findByProductIdContains(String searchId, Pageable pageable);
	public Page<Product> findByMakerCompanyIdContains(String searchId, Pageable pageable);
	
	
	// 제품 리스트에서 사용 중인 companyId 확인 여부
	//boolean existsByMaker_Id(Long companyId);
	
    @Query("SELECT new com.jibro.fulfill.dto.main.ProductSummaryDto(p.productId, p.stockCount, p.safetyStock) FROM Product p")
    List<ProductSummaryDto> findProductSummaries();
}
