package com.jibro.fulfill.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class OngoingController {

	@GetMapping("ongoing/list")
	public ModelAndView ongoingList(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ongoing/list");
		return mav;
	}
}
