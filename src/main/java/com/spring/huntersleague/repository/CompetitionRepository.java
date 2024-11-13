package com.spring.huntersleague.repository;

import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.repository.dto.CompetitionHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
    void deleteById(UUID id);

    @Query(nativeQuery = true, value = "SELECT * FROM get_user_competition_rankings(:userId)")
    List<Object[]> findUserCompetitionRankingsRaw(@Param("userId") UUID userId);
}

