package com.store.Furniture_Home.service;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.store.Furniture_Home.entites.Order;
import com.store.Furniture_Home.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import com.store.Furniture_Home.service.UserManagementService;
import com.store.Furniture_Home.repositrory.CartRepository;
import com.store.Furniture_Home.repositrory.CartItemRepository;
import com.store.Furniture_Home.repositrory.UserRepository;
import com.store.Furniture_Home.entites.Cart;
import com.store.Furniture_Home.entites.User;
import com.store.Furniture_Home.entites.State;
import java.util.List;
import com.store.Furniture_Home.entites.Order_state_change;
import com.store.Furniture_Home.repositrory.order_state_changeRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private final UserManagementService userManagementService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final order_state_changeRepository order_state_changeRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(UserManagementService userManagementService,
     CartRepository cartRepository,
     CartItemRepository cartItemRepository,
      order_state_changeRepository order_state_changeRepository,
       UserRepository userRepository) {
        this.userManagementService = userManagementService;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.order_state_changeRepository = order_state_changeRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Order> getOrder(Long orderId) {
        User currentUser = userManagementService.getCurrentUser();
        Optional<Order> order = orderRepository.findById(orderId);
        
        if (order.isPresent()) {
            Order foundOrder = order.get();
            // Security check: user can only view their own orders
            if (foundOrder.getUser().getId().equals(currentUser.getId())) {
                return ResponseEntity.ok(foundOrder);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    // Create an order for the current customer Cart
    public ResponseEntity<String> createOrder() {
        User currentUser = userManagementService.getCurrentUser();
        Cart cart = currentUser.getCart();
        
        if (cart == null || cart.getCartItems().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is empty");
        }
        
        try {
            Order order = new Order(cart, currentUser);
            orderRepository.save(order);
            
            // Clear cart items
            cart.getCartItems().clear();
            cart.setTotalNoOfItems(0);
            cart.setTotalPrice(0.0);
            cartRepository.save(cart);
            
            userRepository.save(currentUser);
            return ResponseEntity.ok("Order created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error creating order: " + e.getMessage());
        }
    }
    
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok(orders);
    }
    
    public ResponseEntity<String> updateOrder(Long orderId, String str) {
        try {
            Order order = orderRepository.findById(orderId).orElse(null);
            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
            }

            State state = State.valueOf(str);
            order.setState(state);
            Order_state_change order_state_change = new Order_state_change(order, state);
            order_state_changeRepository.save(order_state_change);
            orderRepository.save(order);
            return ResponseEntity.ok("Order updated successfully");
        } 
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid state. Valid states are: Ordered, Shipped, Delivered, Canceled");
        }
    }
    
    public ResponseEntity<List<Order>> getUserOrders() {
        User currentUser = userManagementService.getCurrentUser();
        List<Order> orders = currentUser.getOrders();
        return ResponseEntity.ok(orders);
    }
}
