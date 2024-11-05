package com.spring.huntersleague.web.vm.response.competition;

import java.time.LocalDateTime;
import java.util.UUID;

public class CompetitionDetailsVM {
    private UUID Id;
    private LocalDateTime date;
    private String location;
    private int participantCount;
}
