package com.spring.huntersleague.web.vm.mapper.request.competition;

import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.domain.enums.SpeciesType;
import com.spring.huntersleague.web.vm.request.competition.CompetitionCreateVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {SpeciesType.class})
public interface CompetitionCreateMapper {

    @Mapping(target = "speciesType", expression = "java(SpeciesType.valueOf(competitionCreateVM.getSpeciesType().name()))")
    Competition toEntity(CompetitionCreateVM competitionCreateVM);
}
