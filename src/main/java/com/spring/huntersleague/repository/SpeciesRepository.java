package com.spring.huntersleague.repository;

import com.spring.huntersleague.domain.Species;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    Optional<Species> findById(UUID id);

    @Transactional
    void deleteById(UUID id);

    boolean existsByName(String name);

    boolean existsById(UUID id);
}
