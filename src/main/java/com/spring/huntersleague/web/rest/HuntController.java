package com.spring.huntersleague.web.controller;

import com.spring.huntersleague.domain.Hunt;
import com.spring.huntersleague.service.HuntService;
import com.spring.huntersleague.web.vm.HuntCreateVM;
import com.spring.huntersleague.web.vm.HuntUpdateVM;
import com.spring.huntersleague.web.vm.mapper.HuntCreateMapper;
import com.spring.huntersleague.web.vm.mapper.HuntUpdateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/hunts")
public class HuntController {


    private final HuntService huntService;
    private final HuntCreateMapper huntCreateMapper;
    private final HuntUpdateMapper huntUpdateMapper;

    public HuntController(HuntService huntService, HuntCreateMapper huntCreateMapper, HuntUpdateMapper huntUpdateMapper) {
        this.huntService = huntService;
        this.huntCreateMapper = huntCreateMapper;
        this.huntUpdateMapper = huntUpdateMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Hunt> createHunt(@RequestBody HuntCreateVM huntCreateVM) {
        Hunt hunt = huntCreateMapper.toEntity(huntCreateVM);
        return ResponseEntity.ok(huntService.createHunt(hunt));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Hunt> updateHunt(@RequestBody HuntUpdateVM huntUpdateVM) {
        Hunt hunt = huntUpdateMapper.toEntity(huntUpdateVM);
        return ResponseEntity.ok(huntService.updateHunt(hunt));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Hunt> getHuntById(@PathVariable UUID id) {
        Optional<Hunt> hunt = huntService.getHuntById(id);
        return hunt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHunt(@PathVariable UUID id) {
        huntService.deleteHunt(id);
        return ResponseEntity.noContent().build();
    }
}
