package com.store.Furniture_Home.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.store.Furniture_Home.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/cart")
@PreAuthorize("hasRole('Customer')")
public class CartController {

    private final CartService cartService;
    
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add/{productId}/{quantity}")
    public ResponseEntity<String> addToCart(@PathVariable int productId, @PathVariable int quantity) {
        return cartService.addToCart(productId, quantity);
    }
    
    @GetMapping("/viewItems")
    public ResponseEntity<String> viewItems() {
        return cartService.viewItems();
    }
    
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartItemId) {
        return cartService.removeFromCart(cartItemId);
    }
    
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart() {
        return cartService.clearCart();
    }
    
    @PutMapping("/update/{cartItemId}/{quantity}")
    public ResponseEntity<String> updateQuantity(@PathVariable Long cartItemId, @PathVariable int quantity) {
        return cartService.updateQuantity(cartItemId, quantity);
    }
}