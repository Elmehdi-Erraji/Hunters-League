package com.spring.huntersleague.web.vm.request.species;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpeciesCreateVM {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Species category is required")
    private String category;

    @NotNull(message = "Minimum weight is required")
    @Positive(message = "Minimum weight must be a positive value")
    private Double minimumWeight;

    @NotBlank(message = "Difficulty is required")
    private String difficulty;

    @NotNull(message = "Points are required")
    @Positive(message = "Points must be a positive value")
    private Integer points;
}