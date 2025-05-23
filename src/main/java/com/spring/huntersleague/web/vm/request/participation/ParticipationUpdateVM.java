package com.spring.huntersleague.web.vm.request.participation;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ParticipationUpdateVM {

    @NotNull(message = "ID is required")
    private UUID id;

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Competition ID is required")
    private UUID competitionId;

    private Double score;
}
