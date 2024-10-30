package com.spring.huntersleague.repository;

import com.spring.huntersleague.domain.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    Optional<Species> findById(UUID id);
}
