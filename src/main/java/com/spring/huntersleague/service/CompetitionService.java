package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.repository.CompetitionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompetitionService {

    private CompetitionRepository competitionRepository;

    public Competition createCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Competition updateCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Optional<Competition> getCompetitionById(UUID id) {
        return competitionRepository.findById(id);
    }

    public void deleteCompetition(UUID id) {
        competitionRepository.deleteById(id);
    }
}
