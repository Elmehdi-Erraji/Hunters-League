package com.spring.huntersleague.domain;


import com.spring.huntersleague.domain.enums.Difficulty;
import com.spring.huntersleague.domain.enums.SpeciesType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Name required")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Specie category is required")
    private SpeciesType category;

    @NotNull(message = "Minimum weight is required")
    private Double minimumWeight;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Difficulty is required")
    private Difficulty difficulty;

    @NotNull(message = "Points are required")
    private Integer points;
}