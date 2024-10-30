package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.Species;
import com.spring.huntersleague.service.SpeciesService;
import com.spring.huntersleague.web.vm.SpeciesVM;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {

    private final SpeciesService speciesService;
    public SpeciesController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid SpeciesVM speciesVM) {
        Species species = new Species();
        species.setName(speciesVM.getName());
        species.setCategory(speciesVM.getCategory());
        species.setDifficulty(speciesVM.getDifficulty());
        species.setMinimumWeight(speciesVM.getMinimumWeight());
        species.setPoints(speciesVM.getPoints());
        species.setMinimumWeight(speciesVM.getMinimumWeight());

        speciesService.save(species);

        return ResponseEntity.ok("Species registered successfully");
    }

}
