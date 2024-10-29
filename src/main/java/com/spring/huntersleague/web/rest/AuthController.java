package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.service.AuthService;
import com.spring.huntersleague.web.vm.UserLoginVM;
import com.spring.huntersleague.web.vm.UserRegisterVM;
import com.spring.huntersleague.web.vm.mapper.UserLoginMapper;
import com.spring.huntersleague.web.vm.mapper.UserRegistrationMapper;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRegistrationMapper userRegistrationMapper = UserRegistrationMapper.INSTANCE;
    private final UserLoginMapper userLoginMapper = UserLoginMapper.INSTANCE;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginVM userLoginVM) {
        User user = userLoginMapper.toEntity(userLoginVM);

        boolean isAuthenticated = authService.login(user);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegisterVM registrationVM) {
        User user = userRegistrationMapper.toEntity(registrationVM);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setJoinDate(LocalDateTime.now());
        authService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }
}