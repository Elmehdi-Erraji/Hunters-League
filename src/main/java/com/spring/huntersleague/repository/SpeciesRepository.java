package com.spring.huntersleague.repository;

import com.spring.huntersleague.domain.Species;
import jakarta.transaction.Transactional;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    Optional<Species> findById(UUID id);

    @Transactional
    void deleteById(UUID id);

    boolean existsByName(String name);

    boolean existsById(UUID id);


    @Modifying
    @Query("DELETE FROM Hunt h WHERE h.species.id = :speciesId")
    void deleteHuntsBySpeciesId(@Param("speciesId") UUID speciesId);



}
