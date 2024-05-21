package com.jibro.fulfill.dto.order;

import lombok.Getter;

@Getter
public class OrderRequestDto {

    private String orderId;
    private String from;
    private String to;
    
    public OrderRequestDto(OrderListRequestDto dto) {
    	if(dto.getOrderId() == null) {
    		this.orderId = "";
    	} else {
    		this.orderId = dto.getOrderId();
    	}
    	if(dto.getFrom() == null) {
    		this.from = "";
    	} else {
    		this.from = dto.getFrom().toString().substring(0,10);
    	} 
    	if(dto.getTo() == null) {
    		this.to = "";
    	} else {
    		this.to = dto.getTo().toString().substring(0,10);
    	} 
    }
}
