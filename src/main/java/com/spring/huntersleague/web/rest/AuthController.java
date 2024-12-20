package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.domain.enums.Role;
import com.spring.huntersleague.service.AuthService;
import com.spring.huntersleague.web.vm.request.user.UserLoginVM;
import com.spring.huntersleague.web.vm.request.user.UserRegisterVM;
import com.spring.huntersleague.web.vm.mapper.request.user.UserLoginMapper;
import com.spring.huntersleague.web.vm.mapper.request.user.UserRegistrationMapper;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRegistrationMapper userRegistrationMapper;
    private final UserLoginMapper userLoginMapper;

    public AuthController(AuthService authService,UserLoginMapper userLoginMapper,UserRegistrationMapper userRegistrationMapper) {
        this.authService = authService;
        this.userLoginMapper = userLoginMapper;
        this.userRegistrationMapper = userRegistrationMapper;
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
        user.setRole(Role.MEMBER);
        user.setLicenseExpirationDate(LocalDate.of(2026, 1, 1).atStartOfDay());
        authService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }
}