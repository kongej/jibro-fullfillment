package com.jibro.fulfill.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.product.ProductInsertDto;
import com.jibro.fulfill.dto.product.ProductListResponseDto;
import com.jibro.fulfill.dto.product.ProductModDto;
import com.jibro.fulfill.dto.product.ProductModResponseDto;
import com.jibro.fulfill.dto.product.ProductReadResponseDto;
import com.jibro.fulfill.entity.Product;
import com.jibro.fulfill.repository.ProductRepository;
import com.jibro.fulfill.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	private ProductRepository productRepository;
	
	private ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	//list
	@Override
	public List<ProductListResponseDto> productList(String productName, Integer page){
		
		final int pageSize = 10;
		
		List<Product> products;
		
		if(page == null) {
			page = 0;
		}else {
			page -= 1;
		}
		
		if(productName == null) {
			Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "productId");
			products = this.productRepository.findAll(pageable).toList();
		}else {
			Pageable pageable = PageRequest.of(page, pageSize);
			Sort sort = Sort.by(Order.desc("created_At"));
			pageable.getSort().and(sort);
			products = this.productRepository.findByProductNameContains(productName, pageable);
		}
		
		
		//String productId, String productName, Integer cost, Integer safetyStock, 
		// Integer stockCount, Integer defectiveCount, 
		// String productImage, String maker
		return products.stream().map(product -> new ProductListResponseDto(
				product.getProductId(), product.getProductName(), product.getCost(),
				product.getSafetyStock(), product.getStockCount(), product.getDefectiveCount(),
			    product.getProductImage(), product.getMaker(), product.getMaker().getCompanyId())).collect(Collectors.toList());
	}
	
	// insert
	@Override
	public String insert(ProductInsertDto productInsertDto) {
		
//		if(!companyRepository.existsById(productInsertDto.getMaker())) {
//			throw new IllegalArgumentException("관련 거래처는 존재하지 않습니다.");
//		}
		
		Product product = Product.builder()
				.productId(productInsertDto.getProductId())
				.productName(productInsertDto.getProductName())
				.cost(productInsertDto.getCost())
				.safetyStock(productInsertDto.getSafetyStock())
				.stockCount(productInsertDto.getStockCount())
				.defectiveCount(productInsertDto.getDefectiveCount())
				.productImage(productInsertDto.getProductImage())
				.maker(productInsertDto.getMaker())
				.build();
		
		this.productRepository.save(product);
		return product.getProductId();
	}

	
	//read
	@Override
	public ProductReadResponseDto read(String productId) throws NoSuchElementException{
		Product product = this.productRepository.findById(productId).orElseThrow();
		ProductReadResponseDto productReadResponseDto = new ProductReadResponseDto();
		
		productReadResponseDto.fromProduct(product);		
		return productReadResponseDto;
	}
	
	//mod_list
	@Override
	public ProductModResponseDto edit(String productId) throws NoSuchElementException{
		Product product = this.productRepository.findById(productId).orElseThrow();
		
		return ProductModResponseDto.ProductFactory(product);
	}
	
	//mod
	@Override
	public void update(ProductModDto productModDto) throws NoSuchElementException{
		Product product = this.productRepository.findById(productModDto.getProductId()).orElseThrow();
		product = productModDto.fill(product);
		this.productRepository.save(product);
	}
	
	// delete
	@Override
	public void delete(String productId) throws NoSuchElementException{
		Product product = this.productRepository.findById(productId).orElseThrow();
		this.productRepository.delete(product);
	}



}
