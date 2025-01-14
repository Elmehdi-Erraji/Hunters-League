package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.repository.UserRepository;
import com.spring.huntersleague.web.errors.user.UserAlreadyExistException;
import com.spring.huntersleague.web.errors.user.UserNotFoundException;
import com.spring.huntersleague.web.vm.request.user.SearchCriteria;
import com.spring.huntersleague.web.vm.response.profile.UserProfileVM;
import com.spring.huntersleague.web.vm.response.user.UserMapper;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public User save(User user) {
        validateUser(user);
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        if (user.getId() == null || !userRepository.existsById(user.getId())) {
            throw new UserNotFoundException("User with ID " + user.getId() + " not found.");
        }
        validateUser(user);
        return userRepository.save(user);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }



    @Transactional
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    private void validateUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("Username " + user.getUsername() + " is already in use.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("Email " + user.getEmail() + " is already in use.");
        }

    }

    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<List<User>> searchUsers(SearchCriteria criteria) {
        return Optional.ofNullable(userRepository.searchByCriteria(criteria.getUsername(), criteria.getCin()));
    }

    public UserProfileVM getUserProfile(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Username " + username + " not found."));

        return userMapper.toUserProfileDTO(user);
    }

    public Long count(){
        return userRepository.count();
    }
}