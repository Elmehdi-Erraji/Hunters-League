package com.spring.huntersleague.web.rest;

import com.spring.huntersleague.domain.Species;
import com.spring.huntersleague.service.SpeciesService;
import com.spring.huntersleague.web.errors.species.SpeciesNotFoundException;
import com.spring.huntersleague.web.vm.mapper.response.species.SpeciesListMapper;
import com.spring.huntersleague.web.vm.request.species.SpeciesCreateVM;
import com.spring.huntersleague.web.vm.request.species.SpeciesUpdateVM;
import com.spring.huntersleague.web.vm.mapper.request.species.SpeciesCreateMapper;
import com.spring.huntersleague.web.vm.mapper.request.species.SpeciesUpdateMapper;
import com.spring.huntersleague.web.vm.response.species.SpeciesListVM;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {

    private final SpeciesService speciesService;
    private final SpeciesCreateMapper speciesCreateMapper;
    private final SpeciesUpdateMapper speciesUpdateMapper;
    private final SpeciesListMapper speciesListMapper;

    public SpeciesController(SpeciesService speciesService, SpeciesCreateMapper speciesCreateMapper, SpeciesUpdateMapper speciesUpdateMapper, SpeciesListMapper speciesListMapper) {
        this.speciesService = speciesService;
        this.speciesCreateMapper = speciesCreateMapper;
        this.speciesUpdateMapper = speciesUpdateMapper;
        this.speciesListMapper = speciesListMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid SpeciesCreateVM speciesCreateVM) {
        Species species = speciesCreateMapper.toEntity(speciesCreateVM);
        Species savedSpecies = speciesService.save(species);
        UUID createdSpeciesId = savedSpecies.getId();
        return ResponseEntity.ok("Species registered successfully with ID: " + createdSpeciesId);
    }
        @GetMapping("/{id}")
    public ResponseEntity<Species> getSpeciesById(@PathVariable UUID id) {
        Species species = speciesService.findById(id)
                .orElseThrow(() -> new SpeciesNotFoundException("Species with ID not found"));
        return ResponseEntity.ok(species);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpecies(@PathVariable UUID id) {
        Optional<Species> species = speciesService.findById(id);
        if (species.isPresent()) {
            speciesService.delete(id);
            return ResponseEntity.ok("Species deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @Valid @RequestBody SpeciesUpdateVM speciesUpdateVM) {
        Species existingSpecies = speciesService.findById(id)
                .orElseThrow(() -> new SpeciesNotFoundException("Species with ID " + id + " not found"));

        Species updatedSpecies = speciesUpdateMapper.toEntity(speciesUpdateVM);
        updatedSpecies.setId(existingSpecies.getId());

        speciesService.save(updatedSpecies);
        return ResponseEntity.ok("Species updated successfully");
    }

    @GetMapping("/findAll")
    public ResponseEntity<SpeciesListVM> getAllSpecies(Pageable pageable) {
        try {
            Page<Species> speciesPage = speciesService.findAll(pageable);
            List<Species> speciesVMList = speciesListMapper.toSpeciesVMList(speciesPage.getContent());

            SpeciesListVM speciesListVM = new SpeciesListVM(speciesVMList);
            return ResponseEntity.ok(speciesListVM);
        } catch (Exception e) {
            System.err.println("Error occurred while fetching species: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
