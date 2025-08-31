package com.store.Furniture_Home.entites;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_state_change")
public class Order_state_change {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    public Order_state_change(Order order, State state) {
        this.order = order;
        this.state = state;
        this.timestamp = LocalDateTime.now();
    }
    public Order_state_change() {
        this.timestamp = LocalDateTime.now();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
}
