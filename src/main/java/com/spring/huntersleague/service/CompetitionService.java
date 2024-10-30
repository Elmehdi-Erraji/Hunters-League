package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.repository.CompetitionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Optional<Competition> getCompetitionById(int id) {
        return competitionRepository.findById(id);
    }

    public Competition save(Competition competition) {
        return competitionRepository.save(competition);
    }
    public Competition update(Competition competition) {
        return competitionRepository.save(competition);
    }

    public void delete(Competition competition) {
        competitionRepository.delete(competition);
    }
}
