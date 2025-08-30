package com.store.Furniture_Home.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.Furniture_Home.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
