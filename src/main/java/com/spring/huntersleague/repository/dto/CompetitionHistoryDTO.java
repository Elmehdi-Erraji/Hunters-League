package com.spring.huntersleague.repository.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public class CompetitionHistoryDTO {
    private UUID competition_Id;
    private LocalDate competition_Date;
    private Double user_total_score;
    private Integer user_rank;

    public CompetitionHistoryDTO(UUID competition_Id, LocalDate competition_Date, Double user_total_score, Integer user_rank) {
        this.competition_Id = competition_Id;
        this.competition_Date = competition_Date;
        this.user_total_score = user_total_score;
        this.user_rank = user_rank;
    }

    public UUID getCompetition_Id() {
        return competition_Id;
    }

    public void setCompetition_Id(UUID competition_Id) {
        this.competition_Id = competition_Id;
    }

    public LocalDate getCompetition_Date() {
        return competition_Date;
    }

    public void setCompetition_Date(LocalDate competition_Date) {
        this.competition_Date = competition_Date;
    }

    public Double getUser_total_score() {
        return user_total_score;
    }

    public void setUser_total_score(Double user_total_score) {
        this.user_total_score = user_total_score;
    }

    public Integer getUser_rank() {
        return user_rank;
    }

    public void setUser_rank(Integer user_rank) {
        this.user_rank = user_rank;
    }
}
