package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.service.UserService;
import com.spring.huntersleague.web.vm.response.profile.UserProfileVM;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserService userService;
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/myProfile")
    public ResponseEntity<UserProfileVM> getProfile(){
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println(username);
        UserProfileVM userprofile = userService.getUserProfile(username);
        return ResponseEntity.ok(userprofile);
    }
}
