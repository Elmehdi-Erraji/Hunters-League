package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.service.UserService;
import com.spring.huntersleague.web.errors.user.UserNotFoundException;
import com.spring.huntersleague.web.vm.mapper.response.user.UserListMapper;
import com.spring.huntersleague.web.vm.request.user.SearchCriteria;
import com.spring.huntersleague.web.vm.request.user.UserCreateVM;
import com.spring.huntersleague.web.vm.request.user.UserUpdateVM;
import com.spring.huntersleague.web.vm.mapper.request.user.UserCreateMapper;
import com.spring.huntersleague.web.vm.mapper.request.user.UserUpdateMapper;
import com.spring.huntersleague.web.vm.response.user.UserListVM;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;
    private final UserCreateMapper userCreateMapper;
    private final UserUpdateMapper userUpdateMapper;
    private final UserListMapper userListMapper;

    public UsersController(UserService userService, UserCreateMapper userCreateMapper, UserUpdateMapper userUpdateMapper, UserListMapper userListMapper) {
        this.userService = userService;
        this.userCreateMapper = userCreateMapper;
        this.userUpdateMapper = userUpdateMapper;
        this.userListMapper = userListMapper;
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

    @GetMapping("/findAll")
    public ResponseEntity<List<UserListVM>> getAllUsers(Pageable pageable) {
        Page<User> userPage = userService.findAllUsers(pageable);
        List<UserListVM> users = userPage.getContent().stream()
                .map(userListMapper::toViewModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search")
    public List<UserListVM> searchUsers(@RequestParam(required = false) String username,@RequestParam(required = false) String cin) {
        SearchCriteria criteria = new SearchCriteria(username, cin);
        Optional<List<User>> usersOpt = userService.searchUsers(criteria);

        return usersOpt
                .map(users -> users.stream()
                        .map(userListMapper::toViewModel)
                        .collect(Collectors.toList()))
                .orElseGet(List::of);
    }

}