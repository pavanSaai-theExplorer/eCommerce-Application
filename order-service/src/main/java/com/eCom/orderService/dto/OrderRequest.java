package com.eCom.orderService.dto;


import java.math.BigDecimal;

public record OrderRequest(Long id,
                           String orderNumber,
                           String skuCode,
                           BigDecimal price,
                           Integer quantity,
                           String firstName,
                           String lastName,
                           String email) {

}