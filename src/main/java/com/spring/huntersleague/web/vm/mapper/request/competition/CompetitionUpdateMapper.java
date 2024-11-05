package com.spring.huntersleague.web.vm.mapper.request.competition;

import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.domain.enums.SpeciesType;
import com.spring.huntersleague.web.vm.request.competition.CompetitionUpdateVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {SpeciesType.class})
public interface CompetitionUpdateMapper {

    @Mapping(target = "speciesType", expression = "java(SpeciesType.valueOf(competitionUpdateVM.getSpeciesType().name()))")
    Competition toEntity(CompetitionUpdateVM competitionUpdateVM);
}
