package com.jibro.fulfill.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jibro.fulfill.dto.vendor.VendorInsertDto;
import com.jibro.fulfill.dto.vendor.VendorListResponseDto;
import com.jibro.fulfill.dto.vendor.VendorModDto;
import com.jibro.fulfill.dto.vendor.VendorModResponsetDto;
import com.jibro.fulfill.service.VendorService;

@Controller
public class VendorController {
	@Autowired
	private VendorService vendorService;
	
	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}
	
	// 거래처 목록 화면
	@GetMapping("/vendor/list")
	public ModelAndView vendorList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/vendor/list");
		List<VendorListResponseDto> vendors = this.vendorService.vendorList();
		mav.addObject("vendors", vendors);
		return mav;
	}
	
	// 거래처 등록 화면
	@GetMapping("/vendor/insert")
	public String create() {
		return "vendor/insert";
	}
	
	// 거래처 등록 기능
	@PostMapping("/vendor/insert")
	public String insert(VendorInsertDto vendorInsertDto) {
		String companyId = this.vendorService.insert(vendorInsertDto);
		return "redirect:/vendor/list";
	}

	// 거래처 수정 화면
	@GetMapping("/vendor/edit/{companyId}")
	public ModelAndView edit(@PathVariable String companyId) throws NoSuchElementException{
		ModelAndView mav = new ModelAndView();
		VendorModResponsetDto vendorModResponsetDto = this.vendorService.edit(companyId);
		
		mav.addObject("vendorModResponsetDto", vendorModResponsetDto);
		mav.setViewName("/vendor/edit");
		return mav;
	}
	
	// 거래처 수정 기능
	@PostMapping("/vendor/edit/{companyId}")
	public ModelAndView update(@Validated VendorModDto vendorModDto) throws NoSuchElementException{
		ModelAndView mav = new ModelAndView();
		this.vendorService.update(vendorModDto);
		mav.setView(new RedirectView("/vendor/list", true));
		return mav;
	}
	
	// 거래처 삭제 기능
	@PostMapping("/vendor/delete")
	public String delete(String companyId) throws NoSuchElementException{
		this.vendorService.delete(companyId);
		return "redirect:/vendor/list";
	}
}
