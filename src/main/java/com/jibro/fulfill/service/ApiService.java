package com.jibro.fulfill.service;

import com.jibro.fulfill.dto.api.IncomingApiDto;
import com.jibro.fulfill.dto.incoming.IncomingInsertDto;
import com.jibro.fulfill.entity.Incoming;

public interface ApiService {
    String updateStock(String id);
    /* vendor로 product 주문 */
    String productOrder(Incoming incoming);
    /* vendor로 Product 입고 */
    void updateIncoming(IncomingApiDto incomingApiDto);
}
