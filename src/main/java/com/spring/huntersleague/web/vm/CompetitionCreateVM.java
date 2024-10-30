package com.spring.huntersleague.web.vm;

import com.spring.huntersleague.domain.enums.SpeciesType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CompetitionCreateVM {

    @NotBlank(message = "Code is required")
    private String code;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Date is required")
    private LocalDateTime date;

    @NotNull(message = "Species type is required")
    private SpeciesType speciesType;

    @NotNull(message = "Minimum participants are required")
    private Integer minParticipants;

    @NotNull(message = "Maximum participants are required")
    private Integer maxParticipants;

    private Boolean openRegistration;
}
