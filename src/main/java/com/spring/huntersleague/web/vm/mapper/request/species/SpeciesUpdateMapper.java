package com.spring.huntersleague.web.vm.mapper.request.species;

import com.spring.huntersleague.domain.Species;
import com.spring.huntersleague.web.vm.request.species.SpeciesUpdateVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpeciesUpdateMapper {

    @Mapping(target = "category", expression = "java(com.spring.huntersleague.domain.enums.SpeciesType.valueOf(speciesUpdateVM.getCategory().toUpperCase()))")
    @Mapping(target = "difficulty", expression = "java(com.spring.huntersleague.domain.enums.Difficulty.valueOf(speciesUpdateVM.getDifficulty().toUpperCase()))")
    Species toEntity(SpeciesUpdateVM speciesUpdateVM);
}
