package com.jibro.fulfill.dto.sales;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestDTO {
    private String email;
    private String to;
    private String from;
}
