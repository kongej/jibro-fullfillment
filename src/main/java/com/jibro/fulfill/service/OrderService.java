package com.jibro.fulfill.service;

import org.springframework.data.domain.Page;

import com.jibro.fulfill.dto.order.OrderListRequestDto;
import com.jibro.fulfill.dto.order.OrderListResponseDto;
import com.jibro.fulfill.dto.order.OrderReceiveAPIDto;
import com.jibro.fulfill.entity.Order;

public interface OrderService {

	public Page<OrderListResponseDto> getOrders(OrderListRequestDto dto, int page, int size);

	public Order saveOrderData(OrderReceiveAPIDto dto);

	public void doOngoing(String orderId) throws Exception;
}
