package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Participation;
import com.spring.huntersleague.repository.ParticipationRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class ParticipationService {


    private final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public Participation createParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

    public Participation updateParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

    public Optional<Participation> getParticipationById(UUID id) {
        return participationRepository.findById(id);
    }

    public void deleteParticipation(UUID id) {
        participationRepository.deleteById(id);
    }

    public Page<Participation> findAll(Pageable pageable) {
        return participationRepository.findAll(pageable);
    }

    public List<Participation> findParticipationsByUserOrderedByDate(UUID userId) {
        return participationRepository.findByUserIdOrderByCompetitionDateDesc(userId);
    }

    public Page<Participation> findParticipationsForUser(UUID userId, Pageable pageable) {
        return participationRepository.findByUserIdOrderByCompetitionDate(userId,pageable);
    }

    public Long count(){
        return participationRepository.count();
    }
}