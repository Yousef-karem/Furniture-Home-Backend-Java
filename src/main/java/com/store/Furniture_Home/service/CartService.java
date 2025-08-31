package com.store.Furniture_Home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import com.store.Furniture_Home.entites.Cart;
import com.store.Furniture_Home.entites.CartItem;
import com.store.Furniture_Home.entites.User;
import com.store.Furniture_Home.Entity.Product;
import com.store.Furniture_Home.repositrory.CartRepository;
import com.store.Furniture_Home.repositrory.CartItemRepository;
import com.store.Furniture_Home.Repository.ProductRepository;
import com.store.Furniture_Home.repositrory.UserRepository;

@Service
public class CartService {
    private final ProductRepository productRepository;
    private final UserManagementService userManagementService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public CartService(ProductRepository productRepository, UserManagementService userManagementService, CartRepository cartRepository, CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userManagementService = userManagementService;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }
    
    public ResponseEntity<String> addToCart(int productId, int quantity) {
        try {
            if (quantity <= 0) {
                return ResponseEntity.badRequest().body("Quantity must be greater than 0");
            }
            
            User currentUser = userManagementService.getCurrentUser();
            Cart cart = currentUser.getCart();
            
            if (cart == null) {
                cart = new Cart();
                currentUser.setCart(cart);
            }
            
            Product product = productRepository.findById((long) productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
            
            // Check if the product is already in the cart
            CartItem existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals((long) productId))
                .findFirst()
                .orElse(null);
            
            if (existingItem != null) {
                // Update quantity of existing item
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                cart.updateCartItemQuantity(existingItem);
            } else {
                // Create new cart item
                CartItem cartItem = new CartItem(product, quantity, cart);
                cart.addCartItem(cartItem);
            }
            
            // Save the cart first
            cartRepository.save(cart);
            
            // Update the user's cart reference and save the user
            currentUser.setCart(cart);
            userRepository.save(currentUser);
            
            return ResponseEntity.ok("Item added to cart successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    public ResponseEntity<String> viewItems() {
        try {
            User currentUser = userManagementService.getCurrentUser();
            Cart cart = currentUser.getCart();
            
            if (cart == null || cart.getCartItems().isEmpty()) {
                return ResponseEntity.ok("Cart is empty");
            }
            
            return ResponseEntity.ok("Cart items: " + cart.getCartItems().toString());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    public ResponseEntity<String> removeFromCart(Long cartItemId) {
        try {
            User currentUser = userManagementService.getCurrentUser();
            Cart cart = currentUser.getCart();
            
            if (cart == null || cart.getCartItems().isEmpty()) {
                return ResponseEntity.badRequest().body("Cart is empty");
            }
            
            CartItem itemToRemove = cart.getCartItems().stream()
                .filter(item -> item.getId() != null && item.getId().equals(cartItemId))
                .findFirst()
                .orElse(null);
                
            if (itemToRemove == null) {
                return ResponseEntity.badRequest().body("Cart item not found with ID: " + cartItemId);
            }
            
            // Remove the item from the cart
            cart.removeCartItem(itemToRemove);
            
            // Delete the cart item from database
            cartItemRepository.delete(itemToRemove);
            
            // Save the updated cart
            cartRepository.save(cart);
            userRepository.save(currentUser);
            
            return ResponseEntity.ok("Item removed from cart successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    public ResponseEntity<String> clearCart() {
        try {
            User currentUser = userManagementService.getCurrentUser();
            Cart cart = currentUser.getCart();
            
            if (cart == null || cart.getCartItems().isEmpty()) {
                return ResponseEntity.ok("Cart is already empty");
            }
            
            // Get all cart items to delete them from database
            List<CartItem> itemsToDelete = new ArrayList<>(cart.getCartItems());
            
            // Clear the cart
            cart.clearCart();
            
            // Delete all cart items from database
            cartItemRepository.deleteAll(itemsToDelete);
            
            // Save the updated cart
            cartRepository.save(cart);
            userRepository.save(currentUser);
            
            return ResponseEntity.ok("Cart cleared successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    public ResponseEntity<String> updateQuantity(Long cartItemId, int quantity) throws RuntimeException{
        try {
            User currentUser = userManagementService.getCurrentUser();
            Cart cart = currentUser.getCart();
            
            if (cart == null || cart.getCartItems().isEmpty()) {
                return ResponseEntity.badRequest().body("Cart is empty");
            }
            CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId() != null && item.getId().equals(cartItemId))
                .findFirst()
                .orElse(null);
                
            if (cartItem == null) {
                return ResponseEntity.badRequest().body("Cart item not found with ID: " + cartItemId);
            }
            
            if (quantity <= 0) {
                return ResponseEntity.badRequest().body("Quantity must be greater than 0");
            }
            
            cartItem.setQuantity(quantity);
            cartRepository.save(cart);
            userRepository.save(currentUser);
            return ResponseEntity.ok("Quantity updated successfully");
        }
        catch(RuntimeException e)
        {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}