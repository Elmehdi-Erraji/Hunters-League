package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.Hunt;
import com.spring.huntersleague.service.HuntService;
import com.spring.huntersleague.web.vm.mapper.response.hunt.HuntListMapper;
import com.spring.huntersleague.web.vm.request.hunt.HuntCreateVM;
import com.spring.huntersleague.web.vm.request.hunt.HuntUpdateVM;
import com.spring.huntersleague.web.vm.mapper.request.hunt.HuntCreateMapper;
import com.spring.huntersleague.web.vm.mapper.request.hunt.HuntUpdateMapper;
import com.spring.huntersleague.web.vm.response.hunt.HuntListVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hunts")
public class HuntController {


    private final HuntService huntService;
    private final HuntCreateMapper huntCreateMapper;
    private final HuntUpdateMapper huntUpdateMapper;
    private final HuntListMapper huntListMapper;

    public HuntController(HuntService huntService, HuntCreateMapper huntCreateMapper, HuntUpdateMapper huntUpdateMapper, HuntListMapper huntListMapper) {
        this.huntService = huntService;
        this.huntCreateMapper = huntCreateMapper;
        this.huntUpdateMapper = huntUpdateMapper;
        this.huntListMapper = huntListMapper;
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

    @GetMapping("/findAll")
    public ResponseEntity<List<HuntListVM>> getAllHunts(Pageable pageable) {
        Page<Hunt> huntPage = huntService.findAllHunts(pageable);
        List<HuntListVM> hunts = huntPage.getContent().stream()
                .map(huntListMapper::toViewModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(hunts);
    }

}
