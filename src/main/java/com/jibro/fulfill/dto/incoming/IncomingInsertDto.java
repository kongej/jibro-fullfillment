package com.jibro.fulfill.dto.incoming;

import javax.validation.constraints.Positive;

import com.jibro.fulfill.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncomingInsertDto {
	@NonNull
	private Integer orderStatus;
	
	@NonNull
	private String incomingId;
	
	@NonNull
	@Positive
	private Integer incomingCount;
	
	@NonNull
	@Positive
	private Integer total;	
	
	@NonNull
	private Product product;
	
	
}
