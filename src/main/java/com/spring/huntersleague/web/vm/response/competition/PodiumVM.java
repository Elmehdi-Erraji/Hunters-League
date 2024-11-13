package com.spring.huntersleague.web.vm.response.competition;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PodiumVM {

    private UUID userId;
    private String username;
    private double score;
}
