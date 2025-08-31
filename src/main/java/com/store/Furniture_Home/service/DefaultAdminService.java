package com.store.Furniture_Home.service;

import com.store.Furniture_Home.entites.Cart;
import com.store.Furniture_Home.entites.Role;
import com.store.Furniture_Home.entites.User;
import com.store.Furniture_Home.repositrory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultAdminService implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultAdminService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Wait a bit for database to be ready
        Thread.sleep(2000);
        createDefaultAdminIfNotExists();
    }

    private void createDefaultAdminIfNotExists() {
        String adminEmail = "admin@furniture.com";
        
        // Check if admin already exists
        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            try {
                System.out.println("Creating default admin user...");
                
                // Create cart for admin
                Cart adminCart = new Cart();
                adminCart.setTotalNoOfItems(0);
                adminCart.setTotalPrice(0.0f);
                
                // Create admin user
                User adminUser = new User();
                adminUser.setName("System Administrator");
                adminUser.setEmail(adminEmail);
                adminUser.setPassword(passwordEncoder.encode("admin123"));
                adminUser.setPhone("1234567890");
                adminUser.setRole(Role.Admin);
                adminUser.setCart(adminCart);
                
                // Save admin user (this will also save the cart due to cascade)
                userRepository.save(adminUser);
                
                System.out.println("Default admin user created successfully!");
                System.out.println("Email: " + adminEmail);
                System.out.println("Password: admin123");
                System.out.println("Role: Admin");
                System.out.println("User ID: " + adminUser.getId());
                System.out.println("Cart ID: " + adminUser.getCart().getId());
                
            } catch (Exception e) {
                System.err.println("Error creating default admin: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Default admin user already exists.");
            // Verify the existing admin
            try {
                var existingAdmin = userRepository.findByEmail(adminEmail).orElse(null);
                if (existingAdmin != null) {
                    System.out.println("Existing admin details:");
                    System.out.println("ID: " + existingAdmin.getId());
                    System.out.println("Name: " + existingAdmin.getName());
                    System.out.println("Role: " + existingAdmin.getRole());
                    System.out.println("Cart ID: " + (existingAdmin.getCart() != null ? existingAdmin.getCart().getId() : "null"));
                    
                    // Generate and display the correct password hash for testing
                    String correctPasswordHash = passwordEncoder.encode("admin123");
                    System.out.println("Correct password hash for 'admin123': " + correctPasswordHash);
                    
                    // Update the admin password to ensure it's correct
                    existingAdmin.setPassword(correctPasswordHash);
                    userRepository.save(existingAdmin);
                    System.out.println("Admin password updated successfully!");
                }
            } catch (Exception e) {
                System.err.println("Error checking existing admin: " + e.getMessage());
            }
        }
    }
}
