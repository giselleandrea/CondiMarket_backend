package com.condimarket.controllers;

import com.condimarket.dto.CartItemDTO;
import com.condimarket.services.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addToCart(@RequestBody CartItemDTO item) {
        cartService.addToCart(item);
    }

    @DeleteMapping("/remove/{productId}")
    public void removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
    }

    @GetMapping
    public List<CartItemDTO> getCartItems() {
        return cartService.getCartItems();
    }

    @PostMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }
}
