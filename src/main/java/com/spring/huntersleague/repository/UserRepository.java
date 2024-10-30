package com.spring.huntersleague.repository;

import com.spring.huntersleague.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
