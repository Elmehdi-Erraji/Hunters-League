package com.spring.huntersleague.repository;

import com.spring.huntersleague.domain.Hunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface HuntRepository extends JpaRepository<Hunt, UUID> {

    // 1. Method to find all hunt IDs by species ID
    @Query("SELECT h.id FROM Hunt h WHERE h.species.id = :speciesId")
    List<UUID> findHuntIdsBySpeciesId(@Param("speciesId") UUID speciesId);

    // 2. Batch update method to set species_id to 0 for hunts related to a given speciesId
    @Modifying
    @Query("UPDATE Hunt h SET h.species.id = :newSpeciesId WHERE h.species.id = :speciesId AND h.id IN :huntIds")
    void updateHuntsSpeciesIdBatch(@Param("speciesId") UUID speciesId, @Param("huntIds") List<UUID> huntIds);

    // 3. Optional: Method to delete hunts by species_id (if you want to delete after update)
    @Modifying
    @Transactional
    @Query("DELETE FROM Hunt h WHERE h.species.id = :speciesId")
    void deleteHuntsBySpeciesId(@Param("speciesId") UUID speciesId);

    // 4. Optional: Method to find all hunts by species_id (useful for debugging)
    @Query("SELECT h FROM Hunt h WHERE h.species.id = :speciesId")
    List<Hunt> findAllHuntsBySpeciesId(@Param("speciesId") UUID speciesId);
}
