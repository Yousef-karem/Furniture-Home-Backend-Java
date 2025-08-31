package com.store.Furniture_Home.Repository;

import com.store.Furniture_Home.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
