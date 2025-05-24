package com.condimarket.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
}
