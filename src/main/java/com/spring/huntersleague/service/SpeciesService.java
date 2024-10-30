package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Species;
import com.spring.huntersleague.repository.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public Species save(Species species) {
        return speciesRepository.save(species);
    }

    public Optional<Species> findById(UUID id) {
        return speciesRepository.findById(id);
    }

    public void delete(Species species) {
        speciesRepository.delete(species);
    }
}
