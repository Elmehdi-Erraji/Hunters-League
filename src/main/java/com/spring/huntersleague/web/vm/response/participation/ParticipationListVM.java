package com.spring.huntersleague.web.vm.response.participation;

import jakarta.validation.constraints.*;

import java.util.UUID;

public class ParticipationListVM {

    @NotNull(message = "ID is required")
    private UUID id;

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Competition ID is required")
    private UUID competitionId;

    private Double score;
}
