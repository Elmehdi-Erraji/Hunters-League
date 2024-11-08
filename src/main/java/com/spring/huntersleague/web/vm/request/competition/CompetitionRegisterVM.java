package com.spring.huntersleague.web.vm.request.competition;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionRegisterVM {

    @NotNull(message = "Member ID is required")
    private UUID memberID;
}
