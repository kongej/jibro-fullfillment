package com.jibro.fulfill.dto.order;


import com.jibro.fulfill.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderSendAPIDto {

    /* 주문코드(pk) */
    private String orderId;

    /* 현 상태 */
    private int status;

    /* 송장번호 */
    private Integer invc;

}
