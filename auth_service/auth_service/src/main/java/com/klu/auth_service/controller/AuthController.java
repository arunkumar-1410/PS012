package com.klu.auth_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.auth_service.dto.AuthResponse;
import com.klu.auth_service.dto.LoginRequest;
import com.klu.auth_service.dto.RegisterRequest;
import com.klu.auth_service.dto.UserResponse;
import com.klu.auth_service.entity.User;
import com.klu.auth_service.service.JwtService;
import com.klu.auth_service.service.UserService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a student account")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        User user = new User();
        user.setName(request.getName().trim());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User savedUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(savedUser));
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate a user and receive a JWT")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.authenticateUser(request.getEmail(), request.getPassword());
        String token = jwtService.generateToken(user);

        AuthResponse response = new AuthResponse(
                token,
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    @Operation(
            summary = "Get the authenticated user's profile",
            security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UserResponse> profile(@AuthenticationPrincipal Jwt jwt) {
        User user = userService.findByEmail(jwt.getSubject());
        return ResponseEntity.ok(UserResponse.from(user));
    }
}
