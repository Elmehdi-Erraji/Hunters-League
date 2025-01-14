package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.repository.dto.CompetitionHistoryDTO;
import com.spring.huntersleague.repository.dto.PodiumDto;
import com.spring.huntersleague.service.CompetitionService;
import com.spring.huntersleague.web.vm.mapper.response.competition.CompetitionListMapper;
import com.spring.huntersleague.web.vm.mapper.response.competition.CompetitionMapper;
import com.spring.huntersleague.web.vm.request.competition.CompetitionCreateVM;
import com.spring.huntersleague.web.vm.request.competition.CompetitionRegisterVM;
import com.spring.huntersleague.web.vm.request.competition.CompetitionUpdateVM;
import com.spring.huntersleague.web.vm.mapper.request.competition.CompetitionCreateMapper;
import com.spring.huntersleague.web.vm.mapper.request.competition.CompetitionUpdateMapper;
import com.spring.huntersleague.web.vm.request.competition.ScoreSubmissionRequest;
import com.spring.huntersleague.web.vm.response.competition.CompetitionDetailsVM;
import com.spring.huntersleague.web.vm.response.competition.CompetitionListVM;
import com.spring.huntersleague.web.vm.response.competition.CompetitionResultVM;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.ObjenesisHelper;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;
    private final CompetitionCreateMapper competitionCreateMapper;
    private final CompetitionUpdateMapper competitionUpdateMapper;
    private final CompetitionListMapper competitionListMapper;
    private final CompetitionMapper competitionMapper;

    public CompetitionController(CompetitionService competitionService,CompetitionCreateMapper competitionCreateMapper, CompetitionUpdateMapper competitionUpdateMapper, CompetitionListMapper competitionListMapper, CompetitionMapper competitionMapper)
    {
        this.competitionService = competitionService;
        this.competitionCreateMapper = competitionCreateMapper;
        this.competitionUpdateMapper = competitionUpdateMapper;
        this.competitionListMapper = competitionListMapper;
        this.competitionMapper = competitionMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Competition> createCompetition(@RequestBody CompetitionCreateVM competitionCreateVM) {
        Competition competition = competitionCreateMapper.toEntity(competitionCreateVM);
        return ResponseEntity.ok(competitionService.createCompetition(competition));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Competition> updateCompetition(@RequestBody CompetitionUpdateVM competitionUpdateVM) {
        Competition competition = competitionUpdateMapper.toEntity(competitionUpdateVM);
        return ResponseEntity.ok(competitionService.updateCompetition(competition));
    }

//    @GetMapping("/find/{id}")
//    public ResponseEntity<CompetitionDetailsVM> getCompetitionId(@PathVariable UUID id) {
//        Optional<Competition> competition = competitionService.findById(id);
//        return competition.map(comp -> {
//            CompetitionDetailsVM vm = competitionMapper.toViewModel(comp);
//            vm.setParticipantCount(comp.getParticipations() != null ? comp.getParticipations().size() : 0);
//            return ResponseEntity.ok(vm);
//        }).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Competition> getCompetitionId(@PathVariable UUID id){
        Optional<Competition> competition = competitionService.findById(id);
        return ResponseEntity.ok(competition.get());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable UUID id) {
        competitionService.deleteCompetition(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<Map<String, Object>> getAllCompetitions(Pageable pageable) {
        Page<Competition> competitionPage = competitionService.findAll(pageable);
        List<CompetitionListVM> competitions = competitionPage.getContent().stream()
                .map(competitionListMapper::toViewModel)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("competitions", competitions);
        response.put("totalPages", competitionPage.getTotalPages());
        response.put("totalElements", competitionPage.getTotalElements());
        response.put("pageNumber", competitionPage.getNumber());
        response.put("pageSize", competitionPage.getSize());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/{id}")
    public ResponseEntity<String> registerForCompetition(@PathVariable UUID id, @Valid @RequestBody CompetitionRegisterVM registerVM) {
        competitionService.registerMember(id, registerVM.getMemberID());
        return ResponseEntity.ok("Member registered successfully.");
    }

    @PostMapping("/submitScores")
    public ResponseEntity<String> submitScores(@RequestBody ScoreSubmissionRequest scoreSubmissionRequest) {

        if (!competitionService.isJury(scoreSubmissionRequest.getJuryUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: Only jury members can submit scores.");
        }
        competitionService.submitScores(scoreSubmissionRequest);
        return ResponseEntity.ok("Scores recorded successfully.");
    }

    @GetMapping("/{userId}/competitionResults")
    public ResponseEntity<List<CompetitionResultVM>> getCompetitionResults(@PathVariable UUID userId) {
        List<CompetitionResultVM> results = competitionService.getCompetitionResults(userId);
        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(results);
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{competitionId}/podium")
    public List<PodiumDto> getTopThreeParticipants(@PathVariable UUID competitionId) {
        return competitionService.getTopThreeParticipants(competitionId);
    }

    @GetMapping("/rankings/{userId}")
    public List<CompetitionHistoryDTO> getUserCompetitionRankings(@PathVariable UUID userId) {
        return competitionService.getUserCompetitionRankings(userId);
    }

}
