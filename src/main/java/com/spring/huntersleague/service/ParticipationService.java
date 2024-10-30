package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Participation;
import com.spring.huntersleague.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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
}