package com.jibro.fulfill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.fulfill.dto.incoming.IncomingListResponseDto;
import com.jibro.fulfill.service.IncomingService;

@Controller
public class IncomingController {
	@Autowired
	IncomingService incomingService;
	
	@GetMapping("incoming/list")
	public ModelAndView incomingList() throws Exception{
		ModelAndView mav = new ModelAndView();
		List<IncomingListResponseDto> incomingList = this.incomingService.incomingList();
		mav.addObject("incomingList",incomingList);
		mav.setViewName("incoming/list");
		return mav;
	}
}
