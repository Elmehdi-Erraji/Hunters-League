package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Species;
import com.spring.huntersleague.repository.SpeciesRepository;
import com.spring.huntersleague.web.errors.species.DuplicateSpeciesException;
import com.spring.huntersleague.web.errors.species.InvalidSpeciesException;
import com.spring.huntersleague.web.errors.species.SpeciesNotFoundException;
import jakarta.transaction.Transactional;
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
        validateSpecies(species);

        if (speciesRepository.existsByName(species.getName())) {
            throw new DuplicateSpeciesException("Species with the same name already exists.");
        }

        return speciesRepository.save(species);
    }

    public Optional<Species> findById(UUID id) {
        if (id == null) {
            throw new InvalidSpeciesException("ID is required.");
        }

        return Optional.ofNullable(speciesRepository.findById(id)
                .orElseThrow(() -> new SpeciesNotFoundException("Species with ID " + id + " not found.")));
    }

    @Transactional
    public void delete(UUID id) {
        if (id == null) {
            throw new InvalidSpeciesException("ID is required for deletion.");
        }
        if (!speciesRepository.existsById(id)) {
            throw new SpeciesNotFoundException("Species with ID " + id + " not found.");
        }

        speciesRepository.deleteById(id);
    }

    private void validateSpecies(Species species) {
        if (species == null || species.getName() == null || species.getCategory() == null ||
                species.getMinimumWeight() == null || species.getDifficulty() == null || species.getPoints() == null) {
            throw new InvalidSpeciesException("Invalid species data: All fields are required.");
        }

        if (species.getPoints() < 0 || species.getMinimumWeight() <= 0) {
            throw new InvalidSpeciesException("Points must be non-negative and minimum weight must be positive.");
        }
    }
}