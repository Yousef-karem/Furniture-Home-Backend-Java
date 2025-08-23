package com.store.Furniture_Home.entites;

import jakarta.persistence.*;

@Entity(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "total_no_of_items", nullable = false)
    private int totalNoOfItems;
    
    @Column(name = "total_price", nullable = false)
    private float totalPrice;
    
    // Default constructor
    public Cart() {
        this.totalNoOfItems = 0;
        this.totalPrice = 0.0f;
    }
    
    // Constructor with parameters
    public Cart(int totalNoOfItems, float totalPrice) {
        this.totalNoOfItems = totalNoOfItems;
        this.totalPrice = totalPrice;
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
    
    public float getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}