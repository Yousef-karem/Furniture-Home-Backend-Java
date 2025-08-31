package com.store.Furniture_Home.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.store.Furniture_Home.service.OrderService;
import com.store.Furniture_Home.entites.Order;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.store.Furniture_Home.entites.State;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // View all user orders (Customer)
    @PreAuthorize("hasRole('Customer')")
    @GetMapping("/my-orders")
    public ResponseEntity<List<Order>> getUserOrders() {
        return orderService.getUserOrders();
    }
    
    // View order details
    @PreAuthorize("hasRole('Customer')")
    @GetMapping("/viewDetails/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }
    
    // Create an order for the current customer Cart
    @PreAuthorize("hasRole('Customer')")
    @PostMapping("/create")
    public ResponseEntity<String> createOrder() {
        return orderService.createOrder();
    }

    // View all orders (Admin)
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/admin/all-orders")
    public ResponseEntity<List<Order>> getOrders() {
        return orderService.getOrders();
    }
    
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/updateState/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable Long orderId, @RequestBody String state) {
            return orderService.updateOrder(orderId, state);
    }
}
