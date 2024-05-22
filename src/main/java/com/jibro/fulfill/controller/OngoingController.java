package com.jibro.fulfill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.fulfill.dto.ongoing.OngoingListResponseDto;
import com.jibro.fulfill.entity.Ongoing;
import com.jibro.fulfill.service.OngoingService;
import com.jibro.fulfill.service.StockService;

@Controller
public class OngoingController {
	@Autowired
	OngoingService ongoingService;
	@Autowired
	StockService stockService;
	
	@GetMapping("ongoing/list")
	public ModelAndView ongoingList() throws Exception{
		ModelAndView mav = new ModelAndView();
		List<OngoingListResponseDto> ongoingList = this.ongoingService.ongoingList();
		mav.addObject("ongoingList",ongoingList);
		mav.setViewName("ongoing/list");
		return mav;
	}
	
	@GetMapping("ongoing/insert")
	public void ongoingInsert() throws Exception{
		
		String orderId = "ORDER002";
		Ongoing ongingData = this.ongoingService.ongoingInsert(orderId);

		String productId = ongingData.getOrder().getProduct().getProductId();
		Integer count = ongingData.getOrder().getCount();
		this.stockService.stockUpdate(productId,count);

	}
}
