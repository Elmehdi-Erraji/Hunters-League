package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.repository.AuthRepository;
import com.spring.huntersleague.service.dto.UserLoginDTO;
import com.spring.huntersleague.service.dto.UserRegistrationDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public User register(User user) {
        return authRepository.save(user);
    }

    public boolean login(User userLogin) {
        User user = authRepository.findByUsername(userLogin.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        if(BCrypt.checkpw(userLogin.getPassword(), user.getPassword())) {
            return true;
        }else{
            throw new IllegalArgumentException("Wrong password");
        }
    }
}
