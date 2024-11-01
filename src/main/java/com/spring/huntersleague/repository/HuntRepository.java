package com.spring.huntersleague.repository;

import com.spring.huntersleague.domain.Hunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HuntRepository extends JpaRepository<Hunt, UUID> {
    @Modifying
    @Query("DELETE FROM Hunt h WHERE h.species.id = :speciesId")
    void deleteBySpeciesId(UUID speciesId);
}
