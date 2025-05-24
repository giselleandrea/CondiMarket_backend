package com.condimarket.controllers;

import com.condimarket.dto.PaymentMethodDTO;
import com.condimarket.services.PaymentMethodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    public PaymentMethodDTO createPaymentMethod(@RequestBody PaymentMethodDTO dto) {
        return paymentMethodService.createPaymentMethod(dto);
    }

    @GetMapping
    public List<PaymentMethodDTO> getAllPaymentMethods() {
        return paymentMethodService.getAllPaymentMethods();
    }
}
