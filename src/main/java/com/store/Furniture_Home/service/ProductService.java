package com.store.Furniture_Home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.store.Furniture_Home.Entity.Product;
import com.store.Furniture_Home.Repository.ProductRepository;
import com.store.Furniture_Home.entites.User;
import com.store.Furniture_Home.entites.Favourite;
import com.store.Furniture_Home.repositrory.UserRepository;
import com.store.Furniture_Home.repositrory.FavouriteRepository;
import java.util.Optional;
import java.util.List;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final UserManagementService userManagementService;
    private final UserRepository userRepository;
    private final FavouriteRepository favouriteRepository;
    
    @Autowired
    public ProductService(ProductRepository productRepository, UserManagementService userManagementService, UserRepository userRepository, FavouriteRepository favouriteRepository) {
        this.productRepository = productRepository;
        this.userManagementService = userManagementService;
        this.userRepository = userRepository;
        this.favouriteRepository = favouriteRepository;
    }
    
    public ResponseEntity<String> addFavorite(Long id) {
        try {
            // Get current user
            User currentUser = userManagementService.getCurrentUser();
            
            // Find the product
            Optional<Product> productOptional = productRepository.findById(id);
            if (!productOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Product not found with ID: " + id);
            }
            
            Product product = productOptional.get();
            
            // Check if the product is already in user's favorites
            if (favouriteRepository.existsByUserAndProduct(currentUser, product)) {
                return ResponseEntity.ok("Product is already in favorites");
            }
            
            // Create a new favourite entry
            Favourite favourite = new Favourite(currentUser, product);
            favouriteRepository.save(favourite);
            
            return ResponseEntity.ok("Product added to favorites successfully");
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    public ResponseEntity<String> removeFavorite(Long id) {
        try {
            // Get current user
            User currentUser = userManagementService.getCurrentUser();
            
            // Find the product
            Optional<Product> productOptional = productRepository.findById(id);
            if (!productOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Product not found with ID: " + id);
            }
            
            Product product = productOptional.get();
            
            // Find and remove the favourite entry
            Optional<Favourite> favouriteOptional = favouriteRepository.findByUserAndProduct(currentUser, product);
            if (!favouriteOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Product is not in favorites");
            }
            
            favouriteRepository.delete(favouriteOptional.get());
            return ResponseEntity.ok("Product removed from favorites successfully");
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    public ResponseEntity<List<Favourite>> getFavorites() {
        try {
            // Get current user
            User currentUser = userManagementService.getCurrentUser();
            
            // Get user's favorites
            List<Favourite> favorites = favouriteRepository.findByUser(currentUser);
            return ResponseEntity.ok(favorites);
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}