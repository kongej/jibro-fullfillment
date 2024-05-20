package com.jibro.fulfill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IncomingController {
	@GetMapping("incoming/list")
	public ModelAndView incomingList(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("incoming/list");
		return mav;
	}
}
