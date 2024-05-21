package com.jibro.fulfill.service;

import com.jibro.fulfill.dto.OrderResponseDto;
import com.jibro.fulfill.entity.Order;
import com.jibro.fulfill.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public ResponseEntity<List<OrderResponseDto>> getOrderList() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/shop/order/list")
                .encode()
                .build()
                .toUri();

        String url = "http://localhost:9090/shop/order/list";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<OrderResponseDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderResponseDto>>() {}
        );

        return responseEntity;
    }
}
