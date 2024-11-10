package com.spring.huntersleague.repository.dto;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PodiumDto {

    private UUID userId;
    private String username;
    private double score;
}
