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

    public User register(UserRegistrationDTO registrationDTO) {
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(BCrypt.hashpw(registrationDTO.getPassword(), BCrypt.gensalt()));
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setCin(registrationDTO.getCin());
        user.setEmail(registrationDTO.getEmail());
        user.setNationality(registrationDTO.getNationality());
        user.setJoinDate(LocalDateTime.now());

        return authRepository.save(user);
    }

    public boolean login(UserLoginDTO loginDTO) {
        User user = authRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        if(BCrypt.checkpw(loginDTO.getPassword(), user.getPassword())) {
            return true;
        }else{
            throw new IllegalArgumentException("Wrong password");
        }
    }
}
