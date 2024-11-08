package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.domain.Hunt;
import com.spring.huntersleague.domain.Participation;
import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.domain.enums.Role;
import com.spring.huntersleague.repository.CompetitionRepository;
import com.spring.huntersleague.repository.ParticipationRepository;
import com.spring.huntersleague.web.errors.competitions.CompetitionNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final UserService userService;
    private final ParticipationRepository participationRepository;

    public CompetitionService(CompetitionRepository competitionRepository, UserService userService, ParticipationRepository participationRepository) {
        this.competitionRepository = competitionRepository;
        this.userService = userService;
        this.participationRepository = participationRepository;
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

    @Transactional
    public void registerMember(UUID id, UUID memberID) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Competition not found"));

        User member = userService.findById(memberID)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(!member.getRole().equals(Role.MEMBER)){
            throw new IllegalArgumentException("user is not a member of this competition");
        }

        Participation participation = new Participation();
        participation.setUser(member);
        participation.setCompetition(competition);

        participationRepository.save(participation);

        competition.getParticipations().add(participation);
        competitionRepository.save(competition);
    }
}
