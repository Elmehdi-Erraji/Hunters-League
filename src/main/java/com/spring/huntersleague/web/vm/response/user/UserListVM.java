package com.spring.huntersleague.web.vm.response.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserListVM {

    @NotNull(message = "ID is required")
    private UUID id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "User role is required")
    private String role;

    @NotBlank(message = "CIN is required")
    private String cin;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private int pageSize;
}
