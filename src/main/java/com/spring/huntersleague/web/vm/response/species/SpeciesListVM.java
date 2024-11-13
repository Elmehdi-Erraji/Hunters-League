package com.spring.huntersleague.web.vm.response.species;

import com.spring.huntersleague.domain.Species;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SpeciesListVM {
    private List<Species> species;

    public SpeciesListVM(List<Species> species) {
        this.species = species;
    }
}