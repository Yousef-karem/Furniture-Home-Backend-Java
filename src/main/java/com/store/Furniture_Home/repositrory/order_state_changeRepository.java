package com.store.Furniture_Home.repositrory;

import com.store.Furniture_Home.entites.Order_state_change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface order_state_changeRepository extends JpaRepository<Order_state_change, Long> {
    
}
