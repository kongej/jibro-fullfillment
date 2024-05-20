package com.jibro.fulfill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StockController {

	@GetMapping("stock/list")
	public ModelAndView stockList(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stock/list");
		return mav;
	}
	
}
