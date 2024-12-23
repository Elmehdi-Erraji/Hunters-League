package com.spring.huntersleague.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {


    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    String password;
}