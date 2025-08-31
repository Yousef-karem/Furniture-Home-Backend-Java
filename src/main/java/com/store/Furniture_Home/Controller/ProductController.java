package com.store.Furniture_Home.Controller;

import com.store.Furniture_Home.Entity.Product;
import com.store.Furniture_Home.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import com.store.Furniture_Home.service.ProductService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@PreAuthorize("hasRole('Customer')")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    
    // API 1: view all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // API 2: view product details by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/addFavorite/{id}")
    public ResponseEntity<String> addFavorite(@PathVariable Long id) {
        return productService.addFavorite(id);
    }
    
    @DeleteMapping("/removeFavorite/{id}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long id) {
        return productService.removeFavorite(id);
    }
    
    @GetMapping("/favorites")
    public ResponseEntity<List<com.store.Furniture_Home.entites.Favourite>> getFavorites() {
        return productService.getFavorites();
    }
}
