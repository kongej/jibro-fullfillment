package com.jibro.fulfill.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jibro.fulfill.dto.seller.SellerInsertDto;
import com.jibro.fulfill.dto.seller.SellerListResponseDto;
import com.jibro.fulfill.dto.seller.SellerModDto;
import com.jibro.fulfill.dto.seller.SellerModResponseDto;
import com.jibro.fulfill.service.SellerService;

@Controller
public class SellerController {
	@Autowired
	private SellerService sellerService;
	
	public SellerController(SellerService sellerService) {
		this.sellerService = sellerService;
	}
	
	// 판매자 목록
	@GetMapping(value= {"/seller/list"})
	public ModelAndView sellerList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/seller/list");
		
		List<SellerListResponseDto> sellers = this.sellerService.sellerList();
		
		mav.addObject("sellers", sellers);
		
		return mav;
	}
	
	// 판매자 입력 화면
	@GetMapping("/seller/insert")
	public String create() {
		return "seller/insert";
	}
	
	// 판매자 입력 기능
	@PostMapping("/seller/insert")
	public String insert(SellerInsertDto sellerInsertDto) {
		String companyId = this.sellerService.insert(sellerInsertDto);
		return "redirect:/seller/list";
	}
	
	// 판매자 수정 화면
	@GetMapping("/seller/edit/{companyId}")
	public ModelAndView edit(@PathVariable String companyId) throws NoSuchElementException{
		ModelAndView mav = new ModelAndView();
		SellerModResponseDto sellerModResponseDto = this.sellerService.edit(companyId);
		
		mav.addObject("sellerModResponseDto", sellerModResponseDto);
		mav.setViewName("/seller/edit");
		return mav;
	}
	
	// 판매자 수정 기능
	@PostMapping("/seller/edit/{companyId}")
	public ModelAndView update(@Validated SellerModDto sellerModDto) throws NoSuchElementException{
		ModelAndView mav = new ModelAndView();
		this.sellerService.update(sellerModDto);
		mav.setView(new RedirectView("/seller/list", true));
		return mav;
	}

	// 판매자 삭제 기능
	@PostMapping("/seller/delete")
	public String delete(@RequestParam("companyId") String companyId) throws NoSuchElementException{
		this.sellerService.delete(companyId);
		return "redirect:/seller/list";
	}
}
