package com.store.Furniture_Home.Entity;

import jakarta.persistence.*;
import com.store.Furniture_Home.entites.Favourite;
import java.util.List;
import com.store.Furniture_Home.entites.OrderItem;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Float price;
    private Integer discount;
    private String category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Favourite> favorites;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Float getPrice() { return price; }
    public void setPrice(Float price) { this.price = price; }

    public Integer getDiscount() { return discount; }
    public void setDiscount(Integer discount) { this.discount = discount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public List<Favourite> getFavorites() { return favorites; }
    public void setFavorites(List<Favourite> favorites) { this.favorites = favorites; }
}
