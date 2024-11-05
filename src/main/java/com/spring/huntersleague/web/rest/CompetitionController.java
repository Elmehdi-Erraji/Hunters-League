package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.domain.Hunt;
import com.spring.huntersleague.service.CompetitionService;
import com.spring.huntersleague.web.errors.competitions.CompetitionNotFoundException;
import com.spring.huntersleague.web.errors.user.UserNotFoundException;
import com.spring.huntersleague.web.vm.mapper.response.competition.CompetitionListMapper;
import com.spring.huntersleague.web.vm.mapper.response.competition.CompetitionMapper;
import com.spring.huntersleague.web.vm.request.competition.CompetitionCreateVM;
import com.spring.huntersleague.web.vm.request.competition.CompetitionUpdateVM;
import com.spring.huntersleague.web.vm.mapper.request.competition.CompetitionCreateMapper;
import com.spring.huntersleague.web.vm.mapper.request.competition.CompetitionUpdateMapper;
import com.spring.huntersleague.web.vm.response.competition.CompetitionDetailsVM;
import com.spring.huntersleague.web.vm.response.competition.CompetitionListVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;
    private final CompetitionCreateMapper competitionCreateMapper;
    private final CompetitionUpdateMapper competitionUpdateMapper;
    private final CompetitionListMapper competitionListMapper;
    private final CompetitionMapper competitionMapper;

    public CompetitionController(CompetitionService competitionService,
                                 CompetitionCreateMapper competitionCreateMapper,
                                 CompetitionUpdateMapper competitionUpdateMapper, CompetitionListMapper competitionListMapper, CompetitionMapper competitionMapper)
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

    @GetMapping("/find/{id}")
    public ResponseEntity<CompetitionDetailsVM> getCompetitionId(@PathVariable UUID id) {
        Optional<Competition> competitionOpt = competitionService.findById(id);
        return competitionOpt
                .map(competition -> ResponseEntity.ok(competitionMapper.toViewModel(competition)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable UUID id) {
        competitionService.deleteCompetition(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CompetitionListVM>> getAllCompetitions(Pageable pageable) {
        Page<Competition> competitionPage = competitionService.findAll(pageable);
        List<CompetitionListVM> competitions = competitionPage.getContent().stream()
                .map(competitionListMapper::toViewModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(competitions);
    }
}
