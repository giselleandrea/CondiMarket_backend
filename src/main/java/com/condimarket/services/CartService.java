package com.condimarket.services;

import com.condimarket.dto.CartItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartService {

    private final List<CartItemDTO> cartItems = new ArrayList<>();

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void addToCart(CartItemDTO item) {
        cartItems.add(item);
    }

    public void removeFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProductId().equals(productId));
    }

    public void clearCart() {
        cartItems.clear();
    }
}
