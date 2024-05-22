package com.jibro.fulfill.service.impl;



import com.jibro.fulfill.dto.stock.SellerStockDto;
import com.jibro.fulfill.entity.Order;
import com.jibro.fulfill.entity.Product;
import com.jibro.fulfill.repository.OrderRepository;
import com.jibro.fulfill.repository.ProductRepository;
import com.jibro.fulfill.service.ApiService;
import com.jibro.fulfill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public String updateStock(String orderId) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9002")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new EntityNotFoundException("orderid not found productId : " + orderId));
        Product product = productRepository.findById(order.getProduct().getProductId())
                .orElseThrow(()-> new EntityNotFoundException("updateStock not found with id : " + order.getProduct().getProductId()));

        SellerStockDto sellerStockDto = new SellerStockDto();
        sellerStockDto.setProductId(product.getProductId());
        sellerStockDto.setProductCount(product.getStockCount());

        System.out.println(sellerStockDto.getProductId());

        return webClient.put().uri(uriBuilder -> uriBuilder.path("order/update/stock")
                        .build())
                .bodyValue(sellerStockDto)
                .exchangeToMono(clientResponse -> {
                    if(clientResponse.statusCode().is2xxSuccessful()){
                        System.out.println("수량 데이터 전송 성공");

                        return Mono.just("success");

                    }else {
                        System.out.println("수량 데이터 전송 실패");
                        return Mono.just("fail");
                    }
                })
                .block();

    }
}
