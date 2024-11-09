package com.spring.huntersleague.web.vm.request.competition;

import com.spring.huntersleague.domain.Hunt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreSubmissionRequest {

    private UUID participationId;
    private UUID juryUserId;

    private UUID participantId;
    private List<HuntEntry> hunts;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HuntEntry {
        private UUID speciesId;
        private double weight;
    }
}
