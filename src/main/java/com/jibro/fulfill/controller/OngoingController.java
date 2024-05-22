package com.jibro.fulfill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.fulfill.dto.ongoing.OngoingListPageDto;
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
	public ModelAndView ongoingList(String searchType, String searchId, Integer page) throws Exception{
		ModelAndView mav = new ModelAndView();
		if (page == null) page=1;
		
		OngoingListPageDto ongoingList = this.ongoingService.ongoingList(searchType, searchId, page);
		mav.addObject("ongoingList",ongoingList.getOngoings());
		mav.addObject("lastPage", ongoingList.isLastPage());
		mav.addObject("totalPage", ongoingList.getTotalPage());
		
		mav.addObject("searchId", searchId);
		mav.addObject("page", page);
		mav.addObject("searchType", searchType);
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
