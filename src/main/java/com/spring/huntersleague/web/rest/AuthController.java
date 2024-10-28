package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.service.AuthService;
import com.spring.huntersleague.service.Impl.AuthServiceImpl;
import com.spring.huntersleague.service.dto.UserLoginDTO;
import com.spring.huntersleague.service.dto.UserRegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        boolean isAuthenticated = authService.login(userLoginDTO);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegistrationDTO registrationDTO) {
        authService.register(registrationDTO);
        return ResponseEntity.ok("User registered successfully");
    }
}
