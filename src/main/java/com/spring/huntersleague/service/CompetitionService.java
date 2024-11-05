package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.domain.Hunt;
import com.spring.huntersleague.repository.CompetitionRepository;
import com.spring.huntersleague.web.errors.competitions.CompetitionNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Competition createCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Competition updateCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Optional<Competition> findById(UUID id) {
        return competitionRepository.findById(id);
    }

    public void deleteCompetition(UUID id) {
        competitionRepository.deleteById(id);
    }

    public Page<Competition> findAll(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }
}
