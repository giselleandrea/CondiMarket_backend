package com.condimarket.services;

import com.condimarket.dto.OrderDTO;
import com.condimarket.persistence.entities.Order;
import com.condimarket.persistence.entities.Product;
import com.condimarket.persistence.entities.User;
import com.condimarket.persistence.repositories.OrderRepository;
import com.condimarket.persistence.repositories.ProductRepository;
import com.condimarket.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();

        Optional<User> user = userRepository.findById(orderDTO.getUserId());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        order.setUser(user.get());
        order.setOrderDate(LocalDateTime.now());

        List<Product> products = productRepository.findAllById(orderDTO.getProductIds());
        order.setProducts(products);

        Order savedOrder = orderRepository.save(order);

        OrderDTO result = new OrderDTO();
        result.setId(savedOrder.getId());
        result.setUserId(savedOrder.getUser().getId());
        result.setOrderDate(savedOrder.getOrderDate());
        result.setProductIds(savedOrder.getProducts().stream().map(Product::getId).collect(Collectors.toList()));

        return result;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::toDTO);
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setProductIds(order.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
        return dto;
    }
}
