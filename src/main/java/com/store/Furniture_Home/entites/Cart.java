package com.store.Furniture_Home.entites;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "total_no_of_items", nullable = false)
    private int totalNoOfItems;
    
    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    // Default constructor
    public Cart() {
        this.totalNoOfItems = 0;
        this.totalPrice = 0.0;
        this.cartItems = new ArrayList<>();
    }
    
    // Constructor with parameters
    public Cart(int totalNoOfItems, double totalPrice) {
        this.totalNoOfItems = totalNoOfItems;
        this.totalPrice = totalPrice;
        this.cartItems = new ArrayList<>();
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public int getTotalNoOfItems() {
        return totalNoOfItems;
    }
    
    public void setTotalNoOfItems(int totalNoOfItems) {
        this.totalNoOfItems = totalNoOfItems;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
    public void addCartItem(CartItem cartItem) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(cartItem);
        updateTotals();
    }
    
    public void updateCartItemQuantity(CartItem cartItem) {
        updateTotals();
    }
    
    public void removeCartItem(CartItem cartItem) {
        if (cartItems != null) {
            cartItems.remove(cartItem);
            // Set the cart reference to null to break the relationship
            if (cartItem != null) {
                cartItem.setCart(null);
            }
            updateTotals();
        }
    }
    
    public void clearCart() {
        if (cartItems != null) {
            // Break the relationship for all cart items
            for (CartItem item : cartItems) {
                item.setCart(null);
            }
            cartItems.clear();
            updateTotals();
        }
    }
    
    private void updateTotals() {
        if (cartItems != null && !cartItems.isEmpty()) {
            this.totalNoOfItems = cartItems.size();
            double total = 0.0;
            for (CartItem cartItem : cartItems) {
                if (cartItem != null && cartItem.getProduct() != null) {
                    total += cartItem.getPrice();
                }
            }
            this.totalPrice = total;
        } else {
            this.totalNoOfItems = 0;
            this.totalPrice = 0.0;
        }
    }
}