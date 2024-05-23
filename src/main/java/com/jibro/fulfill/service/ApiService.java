package com.jibro.fulfill.service;

import com.jibro.fulfill.dto.api.IncomingApiDto;
import com.jibro.fulfill.dto.incoming.IncomingInsertDto;
import com.jibro.fulfill.entity.Incoming;
import com.jibro.fulfill.entity.Product;
import com.jibro.fulfill.repository.ProductRepository;

public interface ApiService {
    String updateStock(String id);
    /* vendor로 product 주문 */
    String productOrder(Incoming incoming);
    /* vendor로 Product 입고 */
    Incoming updateIncoming(IncomingApiDto incomingApiDto);
    /* vendor에서 온 정보로 Product 재고 수정*/
    String updateStock(Incoming incoming);

}
