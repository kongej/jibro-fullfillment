package com.jibro.fulfill.dto.product;

import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Product;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductModDto {
	@NonNull
	private String productId;
	
	@NonNull
	private String productName;
	
	@NonNull
	private Integer cost;
	
	private Integer safetyStock; 
	
	private Integer stockCount;	
	
	private Integer defectiveCount;	
		
	private MultipartFile productImage;
	
	@NonNull
	private Company maker;	

}
