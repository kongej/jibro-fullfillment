package com.jibro.fulfill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jibro.fulfill.dto.order.OrderListResponseDto;
import com.jibro.fulfill.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	// 상품 목록 리스트
	@GetMapping(value = { "/orderList" })
	public void getOrderList(Model model, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
		
		Page<OrderListResponseDto> orderPage = orderService.getOrders(page, size);

		model.addAttribute("orderPage", orderPage);

	}

}
