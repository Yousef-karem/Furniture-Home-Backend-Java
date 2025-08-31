package com.store.Furniture_Home.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@PreAuthorize("hasRole('Customer')")
public class CustomerController {

    @GetMapping("/profile")
    public ResponseEntity<String> getCustomerProfile() {
        return ResponseEntity.ok("Customer profile - Customer role required");
    }
}
