package com.spring.huntersleague.repository;

import com.spring.huntersleague.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

    void deleteById(UUID id);

    Optional<Participation> findById(UUID id);
}
