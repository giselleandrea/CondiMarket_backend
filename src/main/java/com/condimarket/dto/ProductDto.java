package com.condimarket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String nameProduct;
    private String referenceProduct;
    private Integer amountProduct;
    private String description;
    private Integer stock;
    private Long categoryId;
    private String categoryName;
    private String image; 
}
