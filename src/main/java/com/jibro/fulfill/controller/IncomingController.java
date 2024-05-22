package com.jibro.fulfill.controller;

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
	
	@GetMapping("incoming/list")
	public ModelAndView incomingList(String searchType, String searchId, Integer page) throws Exception{
		ModelAndView mav = new ModelAndView();
		if (page == null) page=1;
		
		IncomingListPageDto incomingList = this.incomingService.incomingList(searchType, searchId, page);
		mav.addObject("incomingList",incomingList.getIncomings());
		mav.addObject("lastPage", incomingList.isLastPage());
		mav.addObject("totalPage", incomingList.getTotalPage());
		
		mav.addObject("searchId", searchId);
		mav.addObject("page", page);
		mav.addObject("searchType", searchType);
		mav.setViewName("incoming/list");
		
		return mav;
	}
	
	@PostMapping("incoming/insert")
	public String incomingInsert(IncomingInsertDto incomingInsertDto) throws Exception{
		this.incomingService.incomingInsert(incomingInsertDto);
		return String.format("redirect:/incoming/list");
	}
	
}
