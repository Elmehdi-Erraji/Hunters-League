package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.Participation;
import com.spring.huntersleague.service.ParticipationService;
import com.spring.huntersleague.web.vm.mapper.response.participation.ParticipationListMapper;
import com.spring.huntersleague.web.vm.request.participation.ParticipationCreateVM;
import com.spring.huntersleague.web.vm.request.participation.ParticipationUpdateVM;
import com.spring.huntersleague.web.vm.mapper.request.participation.ParticipationCreateMapper;
import com.spring.huntersleague.web.vm.mapper.request.participation.ParticipationUpdateMapper;
import com.spring.huntersleague.web.vm.response.participation.ParticipationListVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {


    private final ParticipationService participationService;
    private final ParticipationCreateMapper participationCreateMapper;
    private final ParticipationUpdateMapper participationUpdateMapper;
    private final ParticipationListMapper participationListMapper;


    public ParticipationController(ParticipationService participationService, ParticipationCreateMapper participationCreateMapper, ParticipationUpdateMapper participationUpdateMapper, ParticipationListMapper participationListMapper) {
        this.participationService = participationService;
        this.participationCreateMapper = participationCreateMapper;
        this.participationUpdateMapper = participationUpdateMapper;
        this.participationListMapper = participationListMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createParticipation(@RequestBody ParticipationCreateVM participationCreateVM) {
        Participation participation = participationCreateMapper.toEntity(participationCreateVM);
        Participation createdParticipation = participationService.createParticipation(participation);

        Map<String, Object> response = new HashMap<>();
        response.put("id", createdParticipation.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Participation> updateParticipation(@RequestBody ParticipationUpdateVM participationUpdateVM) {
        Participation participation = participationUpdateMapper.toEntity(participationUpdateVM);
        return ResponseEntity.ok(participationService.updateParticipation(participation));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Participation> getParticipationById(@PathVariable UUID id) {
        Optional<Participation> participation = participationService.getParticipationById(id);
        return participation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteParticipation(@PathVariable UUID id) {
        participationService.deleteParticipation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ParticipationListVM>> getAllHunts(Pageable pageable) {
        Page<Participation> participationPage = participationService.findAll(pageable);

        List<ParticipationListVM> participations = participationPage.getContent().stream()
                .map(participationListMapper::toViewModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(participations);
    }

    @GetMapping("/findByUser/{id}")
    public ResponseEntity<List<Participation>> findByUser(@PathVariable UUID id){
        List<Participation> participations = participationService.findParticipationsByUserOrderedByDate(id);
        return ResponseEntity.ok(participations);
    }

    @GetMapping("/findForUser/{id}")
    public ResponseEntity<Page<Participation>> find(@PathVariable UUID id,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "2") int size
    ){
        Pageable pageable = PageRequest.of(page,size);
        Page<Participation> participations = participationService.findParticipationsForUser(id,pageable);

        
        return ResponseEntity.ok(participations);


    }

}
