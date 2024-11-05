package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.Participation;
import com.spring.huntersleague.service.ParticipationService;
import com.spring.huntersleague.web.vm.request.participation.ParticipationCreateVM;
import com.spring.huntersleague.web.vm.request.participation.ParticipationUpdateVM;
import com.spring.huntersleague.web.vm.mapper.request.participation.ParticipationCreateMapper;
import com.spring.huntersleague.web.vm.mapper.request.participation.ParticipationUpdateMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {


    private final ParticipationService participationService;
    private final ParticipationCreateMapper participationCreateMapper;
    private final ParticipationUpdateMapper participationUpdateMapper;

    public ParticipationController(ParticipationService participationService, ParticipationCreateMapper participationCreateMapper, ParticipationUpdateMapper participationUpdateMapper) {
        this.participationService = participationService;
        this.participationCreateMapper = participationCreateMapper;
        this.participationUpdateMapper = participationUpdateMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Participation> createParticipation(@RequestBody ParticipationCreateVM participationCreateVM) {
        Participation participation = participationCreateMapper.toEntity(participationCreateVM);
        return ResponseEntity.ok(participationService.createParticipation(participation));
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
}
