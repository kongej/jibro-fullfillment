package com.jibro.fulfill.dto.order;

import java.time.LocalDateTime;

public class OrderListRequestDto {

    private String orderId;
    private LocalDateTime from;
    private LocalDateTime to;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(String from) {
    	if(from == "") {
    		this.from = null;
    	} else {
    		this.from = LocalDateTime.parse(from + "T00:00:00");;
    	}
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(String to) {
    	
    	if(to == "") {
    		this.to = null;
    	} else {
    		this.to = LocalDateTime.parse(to + "T00:00:00");;
    	}
    }
}
