package com.condimarket.services;

import com.condimarket.dto.PaymentMethodDTO;
import com.condimarket.persistence.entities.PaymentMethod;
import com.condimarket.persistence.repositories.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public PaymentMethodDTO createPaymentMethod(PaymentMethodDTO dto) {
        PaymentMethod method = new PaymentMethod();
        method.setMethodName(dto.getName());
        PaymentMethod saved = paymentMethodRepository.save(method);

        PaymentMethodDTO result = new PaymentMethodDTO();
        result.setId(saved.getId());
        result.setName(saved.getMethodName());
        return result;
    }

    public List<PaymentMethodDTO> getAllPaymentMethods() {
        return paymentMethodRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private PaymentMethodDTO toDTO(PaymentMethod method) {
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setId(method.getId());
        dto.setName(method.getMethodName());
        return dto;
    }
}
