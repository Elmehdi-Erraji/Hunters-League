package com.spring.huntersleague.web.vm.response.competition;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class CompetitionDetailsVM {
    private UUID Id;
    private LocalDateTime date;
    private String location;
    private int participantCount;
}
