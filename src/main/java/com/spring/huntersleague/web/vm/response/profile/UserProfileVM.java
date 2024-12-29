package com.spring.huntersleague.web.vm.response.profile;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileVM {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String nationality;
    private LocalDate joinDate;
    private LocalDate licenseExpirationDate;
}
