package com.spring.huntersleague.web.vm.mapper.response.species;

import com.spring.huntersleague.domain.Species;
import com.spring.huntersleague.web.vm.response.species.SpeciesVM;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpeciesListMapper {

    SpeciesVM tospeciesVM(Species species);
    List<Species> toSpeciesVMList(List<Species> speciesList);
}
