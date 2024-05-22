package com.jibro.fulfill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jibro.fulfill.dto.order.OrderListRequestDto;
import com.jibro.fulfill.dto.order.OrderListResponseDto;
import com.jibro.fulfill.dto.order.OrderReceiveAPIDto;
import com.jibro.fulfill.dto.order.OrderRequestDto;
import com.jibro.fulfill.dto.order.OrderResponseDto;
import com.jibro.fulfill.entity.Ongoing;
import com.jibro.fulfill.entity.Order;
import com.jibro.fulfill.service.OngoingService;
import com.jibro.fulfill.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private OngoingService ongoingService;

	// 상품 목록 리스트
	@GetMapping(value = { "/list" })
	public void getOrderList(Model model, @ModelAttribute OrderListRequestDto dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
		
		Page<OrderListResponseDto> orderPage = orderService.getOrders(dto, page - 1, size);
		OrderResponseDto responseDto = new OrderResponseDto(orderPage, 10);
		
		OrderRequestDto requstDto = new OrderRequestDto(dto);
		
		model.addAttribute("orderPage", responseDto);
		model.addAttribute("requestDto", requstDto);
	}

	@PostMapping("/receive-from-seller")
    public ResponseEntity<String> saveOrderData(@RequestBody OrderReceiveAPIDto dto){
		String result = "";
		
		Order order = orderService.saveOrderData(dto);
		
        return ResponseEntity.status(HttpStatus.OK).body(result);
        
    }
	
	@PostMapping("/ongoing")
	public ResponseEntity<String> doOngoing(@RequestParam String orderId){
		
		System.out.println("오더아이디 : " + orderId);
		String resultMesage ="";
		int status;
		
		try {
			orderService.doOngoing(orderId);
			resultMesage = "success";
			status = 200;
		} catch (Exception e) {
			resultMesage = "fail";
			status = 510;
		}
		return ResponseEntity.status(status).body(resultMesage);
	}
	
}
