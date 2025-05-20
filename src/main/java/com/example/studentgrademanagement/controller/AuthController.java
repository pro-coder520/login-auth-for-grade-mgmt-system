package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.dto.request.LoginRequest;
import com.example.studentgrademanagement.dto.request.RegisterRequest;
import com.example.studentgrademanagement.dto.response.AuthResponse;
import com.example.studentgrademanagement.dto.response.MessageResponse;
import com.example.studentgrademanagement.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600) // Allow all origins for simplicity, restrict in production
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        // You might want to add role-based restrictions on who can register whom.
        // e.g., only ADMIN can register another ADMIN or LECTURER.
        // Students might be able to self-register.
        MessageResponse messageResponse = authService.registerUser(registerRequest);
        return ResponseEntity.ok(messageResponse);
    }
}