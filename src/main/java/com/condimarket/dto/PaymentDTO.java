package com.condimarket.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long paymentMethodId;
    private double amount;
}
