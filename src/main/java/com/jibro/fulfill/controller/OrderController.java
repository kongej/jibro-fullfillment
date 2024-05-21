package com.jibro.fulfill.controller;

import com.jibro.fulfill.dto.OrderResponseDto;
import com.jibro.fulfill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

/*    @GetMapping(value = "/order/list")
    public ModelAndView getOrderList(){
        ModelAndView mav = new ModelAndView();

        List<OrderResponseDto> orderList = this.orderService.getOrderList();

        mav.setViewName("/order/list");
        return mav;
    }*/

    @GetMapping(value = "/order/list")
    public ResponseEntity<List<OrderResponseDto>> getOrderList(){
        return this.orderService.getOrderList();
    }
}
