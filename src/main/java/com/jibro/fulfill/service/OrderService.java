package com.jibro.fulfill.service;

import org.springframework.data.domain.Page;

import com.jibro.fulfill.dto.order.OrderListResponseDto;

public interface OrderService {

	public Page<OrderListResponseDto> getOrders(int page, int size);
}
