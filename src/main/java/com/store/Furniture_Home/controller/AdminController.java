package com.store.Furniture_Home.Controller;

import com.store.Furniture_Home.dto.RoleUpdateDto;
import com.store.Furniture_Home.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('Admin')")
public class AdminController {  

    private final UserManagementService userManagementService;

    @Autowired
    public AdminController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }
    @GetMapping("/users")
    public ResponseEntity<String> getAllUsers() {
        return userManagementService.getAllUsers();
    }

    @PostMapping("/users/role")
    public ResponseEntity<String> updateUserRole(@RequestBody RoleUpdateDto roleUpdateDto) {
        return userManagementService.updateUserRole(roleUpdateDto);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        return userManagementService.deleteUser(userId);
    }
}
