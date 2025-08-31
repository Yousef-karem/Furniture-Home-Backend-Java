package com.store.Furniture_Home.entites;

import jakarta.persistence.*;
import com.store.Furniture_Home.Entity.Product;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    
    @Column(name = "quantity", nullable = false)
    private int quantity;
    
    // Default constructor
    public CartItem() {}
    
    public CartItem(Product product, int quantity, Cart cart) {
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public Cart getCart() {
        return cart;
    }
    
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    public float getPrice() {
        return product.getPrice() * quantity;
    }
    
    @Override
    public String toString() {
        return String.format("CartItem{id=%d, product='%s', quantity=%d, price=$%.2f}", 
            id, product.getName(), quantity, getPrice());
    }
}
