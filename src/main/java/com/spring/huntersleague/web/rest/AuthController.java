package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.domain.enums.Role;
import com.spring.huntersleague.service.AuthService;
import com.spring.huntersleague.service.dto.AuthenticationRequestDTO;
import com.spring.huntersleague.service.dto.AuthenticationResponseDTO;
import com.spring.huntersleague.service.dto.RegisterRequestDTO;
import com.spring.huntersleague.service.dto.UserRegistrationDTO;
import com.spring.huntersleague.web.vm.request.user.UserLoginVM;
import com.spring.huntersleague.web.vm.request.user.UserRegisterVM;
import com.spring.huntersleague.web.vm.mapper.request.user.UserLoginMapper;
import com.spring.huntersleague.web.vm.mapper.request.user.UserRegistrationMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody @Valid AuthenticationRequestDTO request) {

        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register( @RequestBody @Valid UserRegistrationDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }
}