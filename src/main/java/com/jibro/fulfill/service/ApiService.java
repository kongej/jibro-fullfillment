package com.jibro.fulfill.service;

import com.jibro.fulfill.dto.ProductOrderDto;
import com.jibro.fulfill.entity.Incoming;
import com.jibro.fulfill.repository.IncomingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityNotFoundException;

@Service
public class ApiService {
    private final IncomingRepository incomingRepository;

    @Autowired
    public ApiService(IncomingRepository incomingRepository){
        this.incomingRepository = incomingRepository;
    }

    public ResponseEntity<ProductOrderDto> productOrder(String incomingId) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Incoming incoming = incomingRepository.findById(incomingId)
                .orElseThrow(() -> new EntityNotFoundException("엔티티를 찾을수 없음"+incomingId));

        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setIncoming_id(incoming.getIncomingId());
        productOrderDto.setProduct_id(String.valueOf(incoming.getProduct()));
        productOrderDto.setIncoming_quantity(String.valueOf(incoming.getIncomingCount()));

        return webClient.post().uri(uriBuilder -> uriBuilder.path("/api/order")
                .queryParam("product_id",productOrderDto.getProduct_id())
                .queryParam("incoming_quantity",productOrderDto.getIncoming_quantity())
                .build())
                .bodyValue(productOrderDto)
                .retrieve()
                .toEntity(ProductOrderDto.class)
                .block();
    }
}
