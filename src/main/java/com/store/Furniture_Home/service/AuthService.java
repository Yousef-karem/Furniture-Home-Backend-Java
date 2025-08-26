package com.store.Furniture_Home.service;

import com.store.Furniture_Home.config.OurUserInfoDetails;
import com.store.Furniture_Home.dto.LoginDto;
import com.store.Furniture_Home.dto.LoginResponseDto;
import com.store.Furniture_Home.dto.RegisterDto;
import com.store.Furniture_Home.entites.User;
import com.store.Furniture_Home.repositrory.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.store.Furniture_Home.dto.ResetPasswordDto;
import com.store.Furniture_Home.dto.UpdateProfileDto;

import java.util.Optional;

import static com.store.Furniture_Home.mapper.UserMapper.toUserRegister;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public ResponseEntity<String> register(RegisterDto registerDto)
    {
        if(userRepository.findByEmail(registerDto.getEmail()).isPresent())
        {
            return ResponseEntity.status(404).body("Error: email is already used");
        }
        User user;
        user=toUserRegister(registerDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "Thank you for registering with us! " +
                "We're excited to have you onboard.\n" );
    }
    public ResponseEntity<LoginResponseDto> login(LoginDto loginDto)
    {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        if(user.isPresent())
        {
            if(passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword()))
            {
                // Create UserDetails for JWT token generation
                OurUserInfoDetails userDetails = new OurUserInfoDetails(user.get());
                String token = jwtService.generateToken(userDetails);
                
                LoginResponseDto response = new LoginResponseDto(
                    token,
                    "Login successful",
                    user.get().getEmail(),
                    user.get().getRole().toString()
                );
                
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    public ResponseEntity<String> logout()
    {
        return ResponseEntity.status(HttpStatus.OK).body("Logout successful");
    }
    public ResponseEntity<String> resetPassword(ResetPasswordDto resetPasswordDto)
    {
        Optional<User> user = userRepository.findByEmail(resetPasswordDto.getEmail());
        if(user.isPresent())
        {
            if(passwordEncoder.matches(resetPasswordDto.getOldPassword(), user.get().getPassword()))
            {
                user.get().setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
                userRepository.save(user.get());
                return ResponseEntity.status(HttpStatus.OK).body("Password reset successful");
            }
        }
        return ResponseEntity.status(401).body("Invalid email or old password");
    }

    public ResponseEntity<String> updateProfile(UpdateProfileDto updateProfileDto) {
        Optional<User> user = userRepository.findByEmail(updateProfileDto.getEmail());
        if(user.isPresent())
        {
            if(passwordEncoder.matches(updateProfileDto.getPassword(), user.get().getPassword()))
            {
                user.get().setName(updateProfileDto.getName());
                user.get().setPhone(updateProfileDto.getPhone());
                userRepository.save(user.get());
                return ResponseEntity.status(HttpStatus.OK).body("Profile updated successfully");
            }
        }
        return ResponseEntity.status(401).body("Invalid old email or old password");
    }
}
