package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.repository.AuthRepository;
import com.spring.huntersleague.service.dto.UserLoginDTO;
import com.spring.huntersleague.service.dto.UserRegistrationDTO;
import com.spring.huntersleague.web.errors.user.InvalidCredentialsException;
import com.spring.huntersleague.web.errors.user.InvalidUserException;
import com.spring.huntersleague.web.errors.user.UserNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService implements AuthServiceInterface {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public User register(User user) {
        if(user == null || user.getUsername() == null || user.getPassword() == null || user.getEmail() == null || user.getPassword().length() < 8 ) {
            throw new InvalidUserException("Invalid user");
        }
        if(authRepository.existsByEmail(user.getEmail()) || authRepository.existsByUsername(user.getUsername())) {
            throw new InvalidUserException("User already exists");
        }
        return authRepository.save(user);
    }

    @Override
    public boolean login(User userLogin) {
        User user = authRepository.findByUsername(userLogin.getUsername())
                .orElseThrow(() -> new UserNotFoundException("user not found."));

        if(BCrypt.checkpw(userLogin.getPassword(), user.getPassword())) {
            return true;
        }else{
            throw new InvalidCredentialsException("Invalid credentials.");
        }
    }
}
