package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.service.CompetitionService;
import com.spring.huntersleague.web.vm.request.competition.CompetitionCreateVM;
import com.spring.huntersleague.web.vm.request.competition.CompetitionUpdateVM;
import com.spring.huntersleague.web.vm.mapper.request.competition.CompetitionCreateMapper;
import com.spring.huntersleague.web.vm.mapper.request.competition.CompetitionUpdateMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;
    private final CompetitionCreateMapper competitionCreateMapper;
    private final CompetitionUpdateMapper competitionUpdateMapper;

    public CompetitionController(CompetitionService competitionService,
                                 CompetitionCreateMapper competitionCreateMapper,
                                 CompetitionUpdateMapper competitionUpdateMapper)
    {
        this.competitionService = competitionService;
        this.competitionCreateMapper = competitionCreateMapper;
        this.competitionUpdateMapper = competitionUpdateMapper;
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

    @GetMapping("/find/{id}")
    public ResponseEntity<Competition> getCompetitionById(@PathVariable UUID id) {
        Optional<Competition> competition = competitionService.getCompetitionById(id);
        return competition.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable UUID id) {
        competitionService.deleteCompetition(id);
        return ResponseEntity.noContent().build();
    }
}
