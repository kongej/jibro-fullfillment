package com.jibro.fulfill.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Value("${upload.directory}")
	private String uploadDirectory;

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

		Product product = new Product();
		product.setProductId(productInsertDto.getProductId());
		product.setProductName(productInsertDto.getProductName());
		product.setCost(productInsertDto.getCost());
		product.setSafetyStock(productInsertDto.getSafetyStock());
		product.setStockCount(productInsertDto.getStockCount());
		product.setDefectiveCount(productInsertDto.getDefectiveCount());
		product.setMaker(productInsertDto.getMaker());

		MultipartFile file = productInsertDto.getProductImage();
		if(file != null && !file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			Path uploadPath = Paths.get(uploadDirectory);
			try {
				byte[] bytes = file.getBytes();
				Path filePath = uploadPath.resolve(fileName);
				Files.write(filePath, bytes);
				String fileUrl = fileName; // 파일 경로 설정
				product.setProductImage(fileUrl); // 파일 경로를 엔티티에 설정
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("File upload failed: " + e.getMessage());
			}
		}

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
	public void update(ProductModDto productModDto) {
		Product product = this.productRepository.findById(productModDto.getProductId()).orElseThrow();

		product.setProductId(productModDto.getProductId());
		product.setProductName(productModDto.getProductName());
		product.setCost(productModDto.getCost());
		product.setSafetyStock(productModDto.getSafetyStock());
		product.setStockCount(productModDto.getStockCount());
		product.setDefectiveCount(productModDto.getDefectiveCount());
		product.setMaker(productModDto.getMaker());

		MultipartFile file = productModDto.getProductImage();
		if(file != null && !file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			Path uploadPath = Paths.get(uploadDirectory);
			try {
				byte[] bytes = file.getBytes();
				Path filePath = uploadPath.resolve(fileName);
				Files.write(filePath, bytes);
				String fileUrl = fileName; // 파일 경로 설정
				product.setProductImage(fileUrl); // 파일 경로를 엔티티에 설정
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("File upload failed: " + e.getMessage());
			}
		}
		this.productRepository.save(product);
	}

	// delete
	@Override
	public void delete(String productId) throws NoSuchElementException{
		Product product = this.productRepository.findById(productId).orElseThrow();
		this.productRepository.delete(product);
	}



}
