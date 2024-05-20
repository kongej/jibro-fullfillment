package com.jibro.fulfill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jibro.fulfill.service.SalesService;

@Controller
@RequestMapping("/sales")
public class SalesController {
	@Autowired
	private SalesService salesService; 
	
	// 상품 목록 리스트
	@GetMapping(value= {"/analysis"})
	public void salesAnalysis() {
//		mav.setViewName("/product/list");
//		
//		List<ProductListResponseDto> products = this.productService.productList(productName, page);
//		
//		mav.addObject("products", products);
//		
//		return mav;
	}
	
}
