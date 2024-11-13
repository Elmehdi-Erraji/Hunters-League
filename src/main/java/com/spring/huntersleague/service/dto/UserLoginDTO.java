package com.spring.huntersleague.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserLoginDTO {

    @NotBlank(message = "Username is required")
    private String username;

    @Size(min =8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d).+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit ")
    private String password;
}
