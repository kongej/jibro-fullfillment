package com.jibro.fulfill.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jibro.fulfill.dto.sales.EmailRequestDTO;
import com.jibro.fulfill.dto.sales.SalesRequestDto;
import com.jibro.fulfill.dto.sales.SalesResponseDto;
import com.jibro.fulfill.dto.sales.SalesSummaryRequestDto;
import com.jibro.fulfill.dto.sales.SalesSummaryResponseDto;
import com.jibro.fulfill.service.SalesService;

@Controller
@RequestMapping("/sales")
public class SalesController {
	@Autowired
	private SalesService salesService; 
	
	@GetMapping(value = { "/summary" })
	public void getOrderSummaries(Model model, @ModelAttribute SalesSummaryRequestDto dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
		
		Page<SalesSummaryResponseDto> salesPage = salesService.getOrderSummaries(dto, page - 1, size);
		SalesResponseDto responseDto = new SalesResponseDto(salesPage, 10);
		
		SalesRequestDto requestDto = new SalesRequestDto(dto);
		
		System.out.println(requestDto.toString());
		model.addAttribute("salesPage", responseDto);
		model.addAttribute("requestDto", requestDto);
	}
	
	@PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO dto) {
		System.out.println("1번");
		LocalDateTime from = null;
		LocalDateTime to = null;
		if(dto.getFrom() != "") {
			from =  LocalDateTime.parse(dto.getFrom() + "T00:00:00");
		}
		if(dto.getTo() != "") {
			to =  LocalDateTime.parse(dto.getTo() + "T00:00:00");
		}
		salesService.sendEmailClick(dto.getEmail(), from, to);
        return ResponseEntity.status(200).body("Email sent successfully!");
    }
}
