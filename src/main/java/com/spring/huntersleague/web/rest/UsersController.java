package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.service.UserService;
import com.spring.huntersleague.web.errors.user.UserNotFoundException;
import com.spring.huntersleague.web.vm.request.user.UserCreateVM;
import com.spring.huntersleague.web.vm.request.user.UserUpdateVM;
import com.spring.huntersleague.web.vm.mapper.request.user.UserCreateMapper;
import com.spring.huntersleague.web.vm.mapper.request.user.UserUpdateMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;
    private final UserCreateMapper userCreateMapper;
    private final UserUpdateMapper userUpdateMapper;

    public UsersController(UserService userService, UserCreateMapper userCreateMapper, UserUpdateMapper userUpdateMapper) {
        this.userService = userService;
        this.userCreateMapper = userCreateMapper;
        this.userUpdateMapper = userUpdateMapper;
    }

    @PostMapping("/api/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateVM userCreateVM) {
        User user = userCreateMapper.toEntity(userCreateVM);
        User createdUser = userService.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateVM userUpdateVM) {
        if (!id.equals(userUpdateVM.getId())) {
            return ResponseEntity.badRequest().build();
        }

        User user = userUpdateMapper.toEntity(userUpdateVM);
        User updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }
}