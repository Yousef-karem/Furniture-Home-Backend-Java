package com.store.Furniture_Home.service;

import com.store.Furniture_Home.repositrory.UserRepository;
import com.store.Furniture_Home.dto.RoleUpdateDto;
import com.store.Furniture_Home.entites.Role;
import com.store.Furniture_Home.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

@Service
public class UserManagementService {

    private final UserRepository userRepository;

    @Autowired
    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> updateUserRole(RoleUpdateDto roleUpdateDto) {
        try {
            // Validate input
            if (roleUpdateDto.getUserEmail() == null || roleUpdateDto.getUserEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("User email is required");
            }

            if (roleUpdateDto.getNewRole() == null || roleUpdateDto.getNewRole().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("New role is required");
            }

            // Find user by email
            Optional<User> userOptional = userRepository.findByEmail(roleUpdateDto.getUserEmail());
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with email '" + roleUpdateDto.getUserEmail() + "' not found");
            }

            User user = userOptional.get();
            String oldRole = user.getRole().toString();

            // Validate role
            try {
                Role newRole = Role.valueOf(roleUpdateDto.getNewRole());
                user.setRole(newRole);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest()
                    .body("Invalid role '" + roleUpdateDto.getNewRole() + "'. Valid roles are: Admin, Customer");
            }

            // Save updated user
            userRepository.save(user);

            return ResponseEntity.ok(
                "User '" + user.getName() + "' role updated successfully from '" + 
                oldRole + "' to '" + user.getRole() + "'"
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error updating user role: " + e.getMessage());
        }
    }

    public ResponseEntity<String> getAllUsers() {
        try {
            var users = userRepository.findAll();
            if (users.isEmpty()) {
                return ResponseEntity.ok("No users found in the system");
            }

            StringBuilder response = new StringBuilder("Users in the system:\n");
            for (User user : users) {
                response.append(String.format("- ID: %d, Name: %s, Email: %s, Role: %s\n", 
                    user.getId(), user.getName(), user.getEmail(), user.getRole()));
            }

            return ResponseEntity.ok(response.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error retrieving users: " + e.getMessage());
        }
    }

    public ResponseEntity<String> deleteUser(Long userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " not found");
            }

            User user = userOptional.get();
            String userName = user.getName();
            
            userRepository.delete(user);
            
            return ResponseEntity.ok("User '" + userName + "' (ID: " + userId + ") deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error deleting user: " + e.getMessage());
        }
    }

    public User getCurrentUser() throws RuntimeException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            Optional<User> user = userRepository.findByEmail(email);
            if(user.isPresent())
            {
                return user.get();
            }
        }
        throw new RuntimeException("User not authenticated");
    }
}
