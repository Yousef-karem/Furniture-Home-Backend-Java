package com.store.Furniture_Home.Repository;

import com.store.Furniture_Home.entites.Order;
import com.store.Furniture_Home.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Find orders by user (for security - users can only see their own orders)
    List<Order> findByUser(User user);
}
