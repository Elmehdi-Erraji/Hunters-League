package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Species;
import com.spring.huntersleague.repository.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public Species save(Species species) {
        return speciesRepository.save(species);
    }

    public Optional<Species> findById(int id) {
        return speciesRepository.findById(id);
    }

    public Species update(Species species) {
        return speciesRepository.save(species);
    }
    public void delete(Species species) {
        speciesRepository.delete(species);
    }
}
