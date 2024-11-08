package com.spring.huntersleague.web.vm.mapper.response.competition;


import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.web.vm.response.competition.CompetitionDetailsVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    @Mappings({
            @Mapping(target = "participantCount", expression = "java(competition.getParticipations() != null ? competition.getParticipations().size() : 0)")
    })
    CompetitionDetailsVM toViewModel(Competition competition);
}