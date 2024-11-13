package com.spring.huntersleague.domain;


import com.spring.huntersleague.domain.enums.Difficulty;
import com.spring.huntersleague.domain.enums.SpeciesType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Species category is required")
    private SpeciesType category;

    @NotNull(message = "Minimum weight is required")
    @Positive(message = "Minimum weight must be a positive value")
    private Double minimumWeight;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Difficulty is required")
    private Difficulty difficulty;

    @NotNull(message = "Points are required")
    @Positive(message = "Points must be a positive value")
    private Integer points;
}