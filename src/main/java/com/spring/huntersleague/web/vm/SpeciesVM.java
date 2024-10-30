package com.spring.huntersleague.web.vm;

import com.spring.huntersleague.domain.enums.Difficulty;
import com.spring.huntersleague.domain.enums.SpeciesType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpeciesVM {

    @NotBlank(message = "Name required")
    private String name;

    @NotNull(message = "Specie category is required")
    private SpeciesType category;

    @NotNull(message = "Minimum weight is required")
    private Double minimumWeight;

    @NotNull(message = "Difficulty is required")
    private Difficulty difficulty;

    @NotNull(message = "Points are required")
    private Integer points;
}