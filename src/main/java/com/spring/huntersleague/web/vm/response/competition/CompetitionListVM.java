package com.spring.huntersleague.web.vm.response.competition;

import com.spring.huntersleague.domain.enums.SpeciesType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CompetitionListVM {

    @NotNull(message = "ID is required")
    private UUID id;

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
