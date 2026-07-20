package com.foodrescue.controller;

import com.foodrescue.dto.LoginRequest;
import com.foodrescue.dto.RegisterRequest;
import com.foodrescue.entity.User;
import com.foodrescue.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import com.foodrescue.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", "Email already exists");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if mobile number already exists
        if (userRepository.findByMobileNumber(request.getMobileNumber()).isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", "Mobile number already exists");
            return ResponseEntity.badRequest().body(response);
        }

        // Create new user with hashed password
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        response.put("message", "Registration successful. Please login with your credentials.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

        // Find user by email
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        // Check if user exists
        if (user == null) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // Verify password using BCrypt
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // Login successful - generate JWT and return it along with user info
        String token = JwtUtil.generateToken(user);

        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        response.put("message", "Login successful");
        response.put("userId", String.valueOf(user.getId()));
        response.put("email", user.getEmail());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}