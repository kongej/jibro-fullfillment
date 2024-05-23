package com.jibro.fulfill.controller;

import com.jibro.fulfill.entity.Incoming;
import com.jibro.fulfill.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.fulfill.dto.incoming.IncomingInsertDto;
import com.jibro.fulfill.dto.incoming.IncomingListPageDto;
import com.jibro.fulfill.service.IncomingService;

@Controller
public class IncomingController {
	@Autowired
	IncomingService incomingService;
	@Autowired
	ApiService apiService;
	
	@GetMapping("incoming/list")
	public ModelAndView incomingList(String startDate, String endDate, String searchType, String searchId, Integer page) throws Exception{
		ModelAndView mav = new ModelAndView();
		if (page == null) page=1;
		
		IncomingListPageDto incomingList = this.incomingService.incomingList(startDate, endDate, searchType, searchId, page);
		mav.addObject("incomingList",incomingList.getIncomings());
		mav.addObject("lastPage", incomingList.isLastPage());
		mav.addObject("totalPage", incomingList.getTotalPage());
		
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		mav.addObject("searchType", searchType);
		mav.addObject("searchId", searchId);
		mav.addObject("page", page);
		mav.setViewName("incoming/list");
		
		return mav;
	}
	
	@PostMapping("incoming/insert")
	public String incomingInsert(IncomingInsertDto incomingInsertDto) throws Exception{
		Incoming incoming = this.incomingService.incomingInsert(incomingInsertDto);
		this.apiService.productOrder(incoming);
		return String.format("redirect:/incoming/list");
	}
	
}
