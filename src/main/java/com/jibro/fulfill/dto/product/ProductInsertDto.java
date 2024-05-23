package com.jibro.fulfill.dto.product;

import com.jibro.fulfill.entity.Company;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductInsertDto {
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
	
	private String maker_id;
	
}
