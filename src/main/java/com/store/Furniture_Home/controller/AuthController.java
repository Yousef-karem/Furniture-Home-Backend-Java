package com.store.Furniture_Home.Controller;

import com.store.Furniture_Home.service.AuthService;
import com.store.Furniture_Home.dto.LoginDto;
import com.store.Furniture_Home.dto.LoginResponseDto;
import com.store.Furniture_Home.dto.RegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.store.Furniture_Home.dto.ResetPasswordDto;
import com.store.Furniture_Home.dto.UpdateProfileDto;
@RestController
@RequestMapping("api/user/")
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
    @PostMapping("logout")
    public ResponseEntity<String> Logout()
    {
        return authService.logout();
    }
    @PostMapping("reset-password")
    public ResponseEntity<String> ResetPassword(@RequestBody ResetPasswordDto resetPasswordDto)
    {
        return authService.resetPassword(resetPasswordDto);
    }
    @PostMapping("update-profile")
    public ResponseEntity<String> UpdateProfile(@RequestBody UpdateProfileDto updateProfileDto)
    {
        return authService.updateProfile(updateProfileDto);
    }
}
