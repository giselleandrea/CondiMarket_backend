package com.condimarket.controllers;

import com.condimarket.persistence.entities.PaymentMethod;
import com.condimarket.persistence.repositories.PaymentMethodRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodController(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @PostMapping
    public PaymentMethod create(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @GetMapping("/user/{userId}")
    public List<PaymentMethod> getByUser(@PathVariable Long userId) {
        return paymentMethodRepository.findByUserId(userId);
    }
}
