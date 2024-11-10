package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.*;
import com.spring.huntersleague.domain.enums.Role;
import com.spring.huntersleague.repository.CompetitionRepository;
import com.spring.huntersleague.repository.ParticipationRepository;
import com.spring.huntersleague.repository.dto.PodiumDto;
import com.spring.huntersleague.web.vm.request.competition.ScoreSubmissionRequest;
import com.spring.huntersleague.web.vm.response.competition.CompetitionResultVM;
import com.spring.huntersleague.web.vm.response.competition.PodiumVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final UserService userService;
    private final ParticipationRepository participationRepository;
    private final HuntService huntService;
    private final SpeciesService speciesService;
    private final ParticipationService participationService;

    public CompetitionService(CompetitionRepository competitionRepository, UserService userService, ParticipationRepository participationRepository, HuntService huntService, SpeciesService speciesService, ParticipationService participationService) {
        this.competitionRepository = competitionRepository;
        this.userService = userService;
        this.participationRepository = participationRepository;
        this.huntService = huntService;
        this.speciesService = speciesService;
        this.participationService = participationService;
    }

    public Competition createCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Competition updateCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Optional<Competition> findById(UUID id) {
        return competitionRepository.findById(id);
    }

    public void deleteCompetition(UUID id) {
        competitionRepository.deleteById(id);
    }

    public Page<Competition> findAll(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }

    @Transactional
    public void registerMember(UUID id, UUID memberID) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Competition not found"));

        User member = userService.findById(memberID)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(!member.getRole().equals(Role.MEMBER)){
            throw new IllegalArgumentException("user is not a member of this competition");
        }

        Participation participation = new Participation();
        participation.setUser(member);
        participation.setCompetition(competition);

        participationRepository.save(participation);

        competition.getParticipations().add(participation);
        competitionRepository.save(competition);
    }

    public boolean isJury(UUID id) {
        return userService.findById(id)
                .map(user -> user.getRole() == Role.JURY)
                .orElse(false);
    }

    public void submitScores(ScoreSubmissionRequest request) {
        double totalScore = 0;

        Participation participation = participationService.getParticipationById(request.getParticipationId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid participation ID"));

        // Verify that the participant ID matches the one associated with the participation
        if (!participation.getUser().getId().equals(request.getParticipantId())) {
            throw new IllegalArgumentException("Participant ID does not match the participation");
        }

        // Process each hunt entry
        for (ScoreSubmissionRequest.HuntEntry huntEntry : request.getHunts()) {
            Species species = speciesService.findById(huntEntry.getSpeciesId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid species ID"));

            // Calculate the weight factor based on species category
            double weightFactor;
            switch (species.getCategory()) {
                case BIG_GAME:
                    weightFactor = 3;
                    break;
                case BIRD:
                    weightFactor = 9;
                    break;
                case SEA:
                    weightFactor = 5;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown species type");
            }

            // Calculate the difficulty factor
            double difficultyFactor;
            switch (species.getDifficulty()) {
                case COMMON:
                    difficultyFactor = 1;
                    break;
                case RARE:
                    difficultyFactor = 2;
                    break;
                case EPIC:
                    difficultyFactor = 3;
                    break;
                case LEGENDARY:
                    difficultyFactor = 5;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown difficulty level");
            }

            // Calculate the total score for the hunt entry
            totalScore += (species.getPoints() + (huntEntry.getWeight() * weightFactor)) * difficultyFactor;

            // Create and save the Hunt record using the HuntService
            Hunt hunt = new Hunt();
            hunt.setParticipation(participation);
            hunt.setSpecies(species);
            hunt.setWeight(huntEntry.getWeight());
            huntService.createHunt(hunt);
        }

        participation.setScore(totalScore);
        participationService.updateParticipation(participation);
    }


    public List<CompetitionResultVM> getCompetitionResults(UUID userId) {
        // Fetch the list of participations by user, ordered by competition date
        List<Participation> participations = participationRepository.findByUserIdOrderByCompetitionDateDesc(userId);

        // Group by competition, sum scores, and map to CompetitionResultVM
        Map<UUID, CompetitionResultVM> resultMap = participations.stream()
                .collect(Collectors.toMap(
                        participation -> participation.getCompetition().getId(),
                        participation -> new CompetitionResultVM(
                                participation.getCompetition().getId(),
                                participation.getScore(),
                                participation.getCompetition().getDate()
                        ),
                        (existing, update) -> {
                            // Sum scores if there are multiple participations in the same competition
                            double newScore = existing.getScore() + update.getScore();
                            existing.setScore(newScore); // Now this should work correctly
                            return existing;
                        }
                ));

        // Convert map values to a list
        return new ArrayList<>(resultMap.values());
    }

    public List<PodiumDto> getTopThreeParticipants(UUID competitionId) {
        return participationRepository.findTopThreeByCompetitionId(competitionId, PageRequest.of(0, 3));
    }




}
