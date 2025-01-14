package com.spring.huntersleague.repository;

import com.spring.huntersleague.domain.Participation;
import com.spring.huntersleague.repository.dto.PodiumDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

    void deleteById(UUID id);

    Optional<Participation> findById(UUID id);


    List<Participation> findByUserIdOrderByCompetitionDateDesc(UUID userId);


    Page<Participation> findByUserIdOrderByCompetitionDate(UUID userId,Pageable  pageable);

    @Query("SELECT new com.spring.huntersleague.repository.dto.PodiumDto(p.user.id, p.user.username, p.score) " +
            "FROM Participation p " +
            "WHERE p.competition.id = :competitionId " +
            "ORDER BY p.score DESC")
    List<PodiumDto> findTopThreeByCompetitionId(UUID competitionId, Pageable pageable);


    List<Participation> findByUserId(UUID userId);
}