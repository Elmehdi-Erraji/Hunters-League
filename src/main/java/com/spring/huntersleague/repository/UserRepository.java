package com.spring.huntersleague.repository;

import com.spring.huntersleague.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(UUID id);
    void deleteById(UUID id);
    boolean existsById(UUID id);
    Optional<Object> findByEmail(String email);
}
