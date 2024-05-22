package com.jibro.fulfill.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jibro.fulfill.dto.product.ProductInsertDto;
import com.jibro.fulfill.dto.product.ProductListResponseDto;
import com.jibro.fulfill.dto.product.ProductModDto;
import com.jibro.fulfill.dto.product.ProductModResponseDto;
import com.jibro.fulfill.dto.product.ProductReadResponseDto;
import com.jibro.fulfill.dto.vendor.VendorListResponseDto;
import com.jibro.fulfill.service.ProductService;
import com.jibro.fulfill.service.VendorService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService; 
	
	@Autowired
	private VendorService vendorService;
	
	// 상품 목록 리스트
	@GetMapping(value= {"/product/list"})
	public ModelAndView productList(String productName, Integer page, ModelAndView mav) {
		mav.setViewName("/product/list");
		
		List<ProductListResponseDto> products = this.productService.productList(productName, page);
		
		mav.addObject("products", products);
		
		return mav;
	}
	
	// 상품 입력 화면
	@GetMapping("/product/insert")
	public ModelAndView create() {
	    List<VendorListResponseDto> makerList = this.vendorService.makerList();
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/product/insert");
	    mav.addObject("makerList", makerList);
	    return mav;
	}
	
	// 상품 입력 기능
	@PostMapping("/product/insert")
	public String insert(ProductInsertDto productInsertDto){
		String productId = this.productService.insert(productInsertDto);	
		return "redirect:/product/list";
	}
	
	// 상품 상세 화면
	@GetMapping("/product/read/{productId}")
	public ModelAndView read(@PathVariable String productId) {
		ModelAndView mav = new ModelAndView();
		
		try {
			ProductReadResponseDto productReadResponseDto = this.productService.read(productId);
			
			mav.addObject("productReadResponseDto", productReadResponseDto);
			mav.setViewName("product/read");
		}catch(NoSuchElementException ex) {
			mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
			mav.addObject("message", "관련 상품이 없습니다");
			mav.addObject("location", "/product/list");
			mav.setViewName("common/error/422");
		}
		
		return mav;
	}
	
	// 상품 수정 화면
	@GetMapping("/product/edit/{productId}")
	public ModelAndView edit(@PathVariable String productId) throws NoSuchElementException{
		ModelAndView mav = new ModelAndView();
		ProductModResponseDto productModResponseDto = this.productService.edit(productId);
		
		mav.addObject("productModResponseDto", productModResponseDto);
		mav.setViewName("product/edit");
		
		return mav;
	}
	
	// 상품 수정 기능
	@PostMapping("/product/edit/{productId}")
	public ModelAndView update(@Validated ProductModDto productModDto, Errors errors) {
		ModelAndView mav = new ModelAndView();
		if (errors.hasErrors()) {
			String errorMsg = errors.getFieldErrors()
									.stream()
									.map(x -> x.getField() + " : " + x.getDefaultMessage())
									.collect(Collectors.joining("\n"));
												
		return this.error422(errorMsg,String.format("/product/edit", productModDto.getProductId()));
		
		}
		
		this.productService.update(productModDto);
		
		mav.setView(new RedirectView("/product/list", true));
		
		return mav;
	}
	
	// 상품 삭제 기능
	@PostMapping("/product/delete")
	public String delete(String productId) throws NoSuchElementException{
	   this.productService.delete(productId);
	   return "redirect:/product/list";
	}
	
	/** error 처리 메서드 **/
	@ExceptionHandler
	public ModelAndView noSuchElementException(NoSuchElementException ex) {		
		return this.error422("등록된 상품 정보가 없습니다", "/product/list");
	}

	private ModelAndView error422(String message, String location) {
		ModelAndView mav = new ModelAndView();
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		mav.addObject("message",message);
		mav.addObject("location",location);
		mav.setViewName("/error/422");
		return mav;
	}
	
}
