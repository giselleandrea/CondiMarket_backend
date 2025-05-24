package com.condimarket.services;

import com.condimarket.dto.PaymentDTO;
import com.condimarket.persistence.entities.Payment;
import com.condimarket.persistence.entities.PaymentMethod;
import com.condimarket.persistence.entities.User;
import com.condimarket.persistence.entities.Order;
import com.condimarket.persistence.repositories.PaymentMethodRepository;
import com.condimarket.persistence.repositories.PaymentRepository;
import com.condimarket.persistence.repositories.UserRepository;
import com.condimarket.persistence.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository, OrderRepository orderRepository, PaymentMethodRepository paymentMethodRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public PaymentDTO createPayment(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        payment.setUser(user);

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        payment.setOrder(order);

        PaymentMethod method = paymentMethodRepository.findById(dto.getPaymentMethodId())
                .orElseThrow(() -> new RuntimeException("PaymentMethod not found"));
        payment.setPaymentMethod(method);

        Payment saved = paymentRepository.save(payment);

        return toDTO(saved);
    }

    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<PaymentDTO> getPaymentById(Long id) {
        return paymentRepository.findById(id).map(this::toDTO);
    }

    private PaymentDTO toDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setUserId(payment.getUser().getId());
        dto.setOrderId(payment.getOrder().getId());
        dto.setPaymentMethodId(payment.getPaymentMethod().getId());
        dto.setAmount(payment.getAmount());
        return dto;
    }
}
