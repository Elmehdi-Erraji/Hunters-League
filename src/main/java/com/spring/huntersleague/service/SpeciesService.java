package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Species;
import com.spring.huntersleague.repository.HuntRepository;
import com.spring.huntersleague.repository.SpeciesRepository;
import com.spring.huntersleague.web.errors.species.DuplicateSpeciesException;
import com.spring.huntersleague.web.errors.species.InvalidSpeciesException;
import com.spring.huntersleague.web.errors.species.SpeciesNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;


@Service
public class SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final HuntRepository huntRepository;

    public SpeciesService(SpeciesRepository speciesRepository, HuntRepository huntRepository) {
        this.speciesRepository = speciesRepository;
        this.huntRepository = huntRepository;
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

    public Page<Species> findAll(Pageable pageable) {
        return speciesRepository.findAll(pageable);
    }

    @Transactional
    public void delete(UUID id) {
        if (id == null) {
            throw new InvalidSpeciesException("ID is required for deletion.");
        }
        if (!speciesRepository.existsById(id)) {
            throw new SpeciesNotFoundException("Species with ID " + id + " not found.");
        }

        // Step 1: Delete related hunts
        huntRepository.deleteBySpeciesId(id); // Ensure this method is implemented in your repository

        // Step 2: Delete the species itself
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