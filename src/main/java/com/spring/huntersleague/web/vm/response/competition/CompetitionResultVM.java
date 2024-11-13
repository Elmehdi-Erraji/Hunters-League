package com.spring.huntersleague.web.vm.response.competition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionResultVM {

    private UUID competitionId;
    private double score;
    private LocalDate competitionDate;

    public CompetitionResultVM(UUID competitionId, Double score, LocalDateTime competitionDateTime) {
        this.competitionId = competitionId;
        this.score = (score != null) ? score : 0.0;
        this.competitionDate = competitionDateTime.toLocalDate();
    }
}