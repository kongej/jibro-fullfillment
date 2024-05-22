package com.jibro.fulfill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.fulfill.dto.stock.StockListPageDto;
import com.jibro.fulfill.dto.stock.StockListResponseDto;
import com.jibro.fulfill.dto.stock.StockUpdateResponseDto;
import com.jibro.fulfill.service.StockService;

@Controller
public class StockController {
	@Autowired
	private StockService stockService;
	
	@GetMapping("stock/list")
	public ModelAndView stockList(String searchId, Integer page) throws Exception{
		ModelAndView mav = new ModelAndView();
		if (page == null) page=1;
		
		StockListPageDto  stockList = this.stockService.stockList(searchId, page);
		mav.addObject("stockList", stockList.getStocks());
		mav.addObject("lastPage", stockList.isLastPage());
		mav.addObject("totalPage", stockList.getTotalPage());
		
		mav.addObject("searchId", searchId);
		mav.addObject("page", page);
		mav.setViewName("stock/list");
		return mav;
	}
	
	@PostMapping("stock/update")
	public ModelAndView stockUpdate(@Validated StockUpdateResponseDto stockUpdateResponseDto, String searchId, Integer page) {
		ModelAndView mav = new ModelAndView();
		this.stockService.safetystockUpdate(stockUpdateResponseDto);
		mav.setViewName(String.format("redirect:/stock/list?searchId="+searchId+"&page="+page));
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
