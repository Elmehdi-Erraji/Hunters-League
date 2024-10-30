package com.spring.huntersleague.web.vm;

import com.spring.huntersleague.domain.enums.Difficulty;
import com.spring.huntersleague.domain.enums.SpeciesType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Setter
@Getter
public class SpeciesUpdateVM {

    @NotNull(message = "ID is required")
    private UUID id;

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
