package com.spring.huntersleague.web.vm.mapper;

import com.spring.huntersleague.domain.Species;
import com.spring.huntersleague.web.vm.SpeciesCreateVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpeciesCreateMapper {

    @Mapping(target = "category", expression = "java(com.spring.huntersleague.domain.enums.SpeciesType.valueOf(speciesCreateVM.getCategory().toUpperCase()))")
    @Mapping(target = "difficulty", expression = "java(com.spring.huntersleague.domain.enums.Difficulty.valueOf(speciesCreateVM.getDifficulty().toUpperCase()))")
    Species toEntity(SpeciesCreateVM speciesCreateVM);
}
