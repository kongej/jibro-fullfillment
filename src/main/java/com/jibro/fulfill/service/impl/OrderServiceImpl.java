package com.jibro.fulfill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.order.OrderListResponseDto;
import com.jibro.fulfill.entity.Order;
import com.jibro.fulfill.repository.OrderRepository;
import com.jibro.fulfill.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private OrderRepository orderRepository;
	
	private OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
    public Page<OrderListResponseDto> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Order> orders = orderRepository.findAll(pageable);
        
        return orders.map(order -> new OrderListResponseDto(
                order.getOrderId(),
                order.getProduct(),
                order.getOrdererName(),
                order.getPhoneNum(),
                order.getAddress(),
                order.getCount(),
                order.getOngoing(),
                order.getOrderStatus(),
                order.getSeller(),
                order.getOrderedDate()
        ));
    }
}
