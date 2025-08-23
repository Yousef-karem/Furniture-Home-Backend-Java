package com.store.Furniture_Home.controller;

import com.store.Furniture_Home.dto.LoginDto;
import com.store.Furniture_Home.dto.LoginResponseDto;
import com.store.Furniture_Home.dto.RegisterDto;
import com.store.Furniture_Home.entites.User;
import com.store.Furniture_Home.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping ("register")
    public ResponseEntity<String> Register(@RequestBody RegisterDto registerDto)
    {
        return authService.register(registerDto);
    }
    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> Login(@RequestBody LoginDto loginDto)
    {
        return authService.login(loginDto);
    }
}
