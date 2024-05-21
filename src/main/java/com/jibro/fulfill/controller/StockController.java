package com.jibro.fulfill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.fulfill.dto.stock.StockListResponseDto;
import com.jibro.fulfill.dto.stock.StockUpdateResponseDto;
import com.jibro.fulfill.service.StockService;

@Controller
public class StockController {
	@Autowired
	private StockService stockService;
	
	@GetMapping("stock/list")
	public ModelAndView stockList() throws Exception{
		ModelAndView mav = new ModelAndView();
		List<StockListResponseDto> stockList = this.stockService.stockList();
		mav.addObject("stockList",stockList);
		mav.setViewName("stock/list");
		return mav;
	}
	
	@PostMapping("stock/update")
	public ModelAndView stockUpdate(@Validated StockUpdateResponseDto stockUpdateResponseDto) {
		ModelAndView mav = new ModelAndView();
		System.out.println("id: "+ stockUpdateResponseDto.getProductId());
		this.stockService.stockUpdate(stockUpdateResponseDto);
		mav.setViewName(String.format("redirect:/stock/list"));
		return mav; 
	}
	
	@PostMapping("incoming/insertForm") 
	public ModelAndView insertForm(@Validated StockListResponseDto stockListResponseDto) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("insertData", stockListResponseDto);
		mav.setViewName("incoming/insertForm");
		return mav;
	}
}
