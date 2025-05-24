package com.condimarket.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String methodName; 
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; 
}
