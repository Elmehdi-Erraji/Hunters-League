package com.spring.huntersleague.web.vm.mapper.response.competition;


import com.spring.huntersleague.domain.Competition;
import com.spring.huntersleague.web.vm.response.competition.CompetitionListVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionListMapper {
    CompetitionListVM toViewModel(Competition competition);
}