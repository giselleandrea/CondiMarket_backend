package com.condimarket.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private List<Long> productIds; // o List<ProductDTO> 
}
