package com.spring.huntersleague.web.vm.response.hunt;

import jakarta.validation.constraints.*;

import java.util.UUID;

public class HuntListVM {
    @NotNull(message = "ID is required")
    private UUID id;

    @NotNull(message = "Species ID is required")
    private UUID speciesId;

    @NotNull(message = "Weight is required")
    private Double weight;

    @NotNull(message = "Participation ID is required")
    private UUID participationId;
}
