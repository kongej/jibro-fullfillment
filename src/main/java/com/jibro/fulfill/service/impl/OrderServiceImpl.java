package com.jibro.fulfill.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jibro.fulfill.dto.order.OrderListRequestDto;
import com.jibro.fulfill.dto.order.OrderListResponseDto;
import com.jibro.fulfill.dto.order.OrderReceiveAPIDto;
import com.jibro.fulfill.dto.order.OrderSendAPIDto;
import com.jibro.fulfill.entity.Company;
import com.jibro.fulfill.entity.Ongoing;
import com.jibro.fulfill.entity.Order;
import com.jibro.fulfill.entity.Product;
import com.jibro.fulfill.repository.CompanyRepository;
import com.jibro.fulfill.repository.OrderRepository;
import com.jibro.fulfill.repository.ProductRepository;
import com.jibro.fulfill.service.OngoingService;
import com.jibro.fulfill.service.OrderService;
import com.jibro.fulfill.specification.OrderSpecification;

import reactor.core.publisher.Mono;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private OngoingService ongoingService;
	

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


	@Override
	public int doOngoing(String orderId) throws Exception{
		
		Ongoing ongoing = ongoingService.ongoingInsert(orderId);
		Order order = orderRepository.getById(orderId);
		
		// api 요청을 풀필먼트 컨트롤러 측에 전달
		WebClient webClient = WebClient.builder()
				.baseUrl("http://localhost:9002")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		OrderSendAPIDto orderSendApiDto = new OrderSendAPIDto();
		orderSendApiDto.setOrderId(orderId);
		orderSendApiDto.setStatus(1);
		orderSendApiDto.setInvc(ongoing.getInvc());
		
		
		webClient.put().uri(uriBuilder -> uriBuilder.path("/order/update/delivery")
						.build())
				.bodyValue(orderSendApiDto)
				.exchangeToMono(clientResponse -> {
					if(clientResponse.statusCode().is2xxSuccessful()){
						System.out.println("데이터 전송 성공");
						order.setOrderStatus(1);
						return Mono.defer(()-> {
							orderRepository.save(order);
							return Mono.just("success");
						});
					}else {
						System.out.println("데이터 전송 실패");
						return Mono.just("fail");
					}
				})
				.block();
		
		
		return order.getOrderStatus();
	}



}
