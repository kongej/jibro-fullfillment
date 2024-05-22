package com.jibro.fulfill.controller;

import com.jibro.fulfill.dto.incoming.IncomingApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class ApiController {
//    private ApiService apiService;
//
//    @Autowired
//    public ApiController(ApiService apiService) {
//        this.apiService = apiService;
//    }
//
//    @PostMapping("/receive-from-seller")
//    public ResponseEntity<IncomingApiDto> orderData(
//            @RequestBody IncomingApiDto incomingApiDto
//    ) {
//        System.out.println(incomingApiDto.getOrderId());
//        System.out.println(incomingApiDto.getAddress());
//        System.out.println(incomingApiDto.getOrdererName());
//        System.out.println(incomingApiDto.getCreatedAt());
//        System.out.println(incomingApiDto.getProductId());
//
//        IncomingApiDto request = new IncomingApiDto();
//        request.setOrderId(incomingApiDto.getOrderId());
//        request.setAddress(incomingApiDto.getAddress());
//        request.setOrdererName(incomingApiDto.getOrdererName());
//        return ResponseEntity.status(HttpStatus.OK).body(request);
//    }

//    @PutMapping("/send-detail")
//    public String body() {
//        return apiService.getinv();
//    }
//
//    @PutMapping("/send-stock")
//    public String stock() {
//        return apiService.updateStock();
//    }
}