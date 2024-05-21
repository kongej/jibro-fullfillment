package com.jibro.fulfill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jibro.fulfill.dto.order.OrderListRequestDto;
import com.jibro.fulfill.dto.order.OrderListResponseDto;
import com.jibro.fulfill.dto.order.OrderReceiveAPIDto;
import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Order;
import com.jibro.fulfill.entity.Product;
import com.jibro.fulfill.repository.CompanyRepository;
import com.jibro.fulfill.repository.OrderRepository;
import com.jibro.fulfill.repository.ProductRepository;
import com.jibro.fulfill.service.OrderService;
import com.jibro.fulfill.specification.OrderSpecification;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CompanyRepository companyRepository;
	

	@Override
    public Page<OrderListResponseDto> getOrders(OrderListRequestDto dto, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderRepository.findAll(OrderSpecification.getOrders(dto.getOrderId(), dto.getFrom(), dto.getTo()), pageable);
    
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
                order.getOrderDate()
        ));
    }


	@Override
	public Order saveOrderData(OrderReceiveAPIDto dto) {
		Product product = productRepository.getById(dto.getProductId());
		Company seller = companyRepository.getById(dto.getSeller());
		
		Order order = Order.builder().orderId(dto.getOrderId()).product(product).ordererName(dto.getOrdererName()).phoneNum(dto.getPhoneNumber()).address(dto.getAddress()).count(dto.getSelectedCount()).seller(seller).orderDate(dto.getCreatedAt()).build();
		
		return orderRepository.save(order);
	}

}
