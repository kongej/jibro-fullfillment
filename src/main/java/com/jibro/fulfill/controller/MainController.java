package com.jibro.fulfill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jibro.fulfill.dto.main.MainResponseDto;
import com.jibro.fulfill.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	MainService mainService;
	
	//메인 화면
	@GetMapping("/main")
	public String main(Model model) {
		MainResponseDto responseDto = new MainResponseDto();
		responseDto.setTodayTotalIncomingCount(1);
		responseDto.setTodayTotalOngoingCount(1);
		responseDto.setStockCount(100);
		responseDto.setSafetyStock(20);
		responseDto.setNewOrder(3);
		model.addAttribute("responseDto", responseDto);
	    return "main/main";
	}
	
}
