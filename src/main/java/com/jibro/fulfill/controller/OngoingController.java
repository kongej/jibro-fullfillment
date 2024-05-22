package com.jibro.fulfill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.fulfill.dto.ongoing.OngoingInsertDto;
import com.jibro.fulfill.dto.ongoing.OngoingListResponseDto;
import com.jibro.fulfill.entity.Ongoing;
import com.jibro.fulfill.service.OngoingService;

@Controller
public class OngoingController {
	@Autowired
	OngoingService ongoingService;
	
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

		String orderId = "ORDER003";
		Ongoing ongingData = this.ongoingService.ongoingInsert(orderId);

	}
}
