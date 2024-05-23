package com.jibro.fulfill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.fulfill.dto.ongoing.OngoingListPageDto;
import com.jibro.fulfill.service.OngoingService;
import com.jibro.fulfill.service.StockService;

@Controller
public class OngoingController {
	@Autowired
	OngoingService ongoingService;
	@Autowired
	StockService stockService;
	
	@GetMapping("ongoing/list")
	public ModelAndView ongoingList(String startDate, String endDate, String searchType, String searchId, Integer page) throws Exception{
		ModelAndView mav = new ModelAndView();
		if (page == null) page=1;
		
		OngoingListPageDto ongoingList = this.ongoingService.ongoingList(startDate, endDate, searchType, searchId, page);
		mav.addObject("ongoingList",ongoingList.getOngoings());
		mav.addObject("lastPage", ongoingList.isLastPage());
		mav.addObject("totalPage", ongoingList.getTotalPage());
		
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		mav.addObject("searchType", searchType);
		mav.addObject("searchId", searchId);
		mav.addObject("page", page);
		mav.setViewName("ongoing/list");
		return mav;
	}
}
