package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.User;

public interface AuthServiceInterface {
    User register(User user);

    boolean login(User userLogin);
}
