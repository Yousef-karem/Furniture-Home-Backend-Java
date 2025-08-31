package com.store.Furniture_Home.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import java.util.List;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private User user;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();
    
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;
    
    @Column(name = "total_price")
    private Double totalPrice;
    
    @Column(name = "date")
    private LocalDateTime date;
    
    // Default constructor
    public Order() {
        this.orderItems = new ArrayList<>();
        this.totalPrice = 0.0;
        this.date = LocalDateTime.now();
    }
    
    public Order(Cart cart, User user) {
        this.user = user;
        this.orderItems = new ArrayList<>();
        this.totalPrice = 0.0;
        
        for(CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem(cartItem.getProduct(), cartItem.getQuantity(), this);
            orderItems.add(orderItem);
            totalPrice += cartItem.getPrice();
        }
        this.state = State.Ordered;
        this.date = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    public State getState() {
        return state;
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public Double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
