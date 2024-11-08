package com.spring.huntersleague.web.vm.mapper.response.participation;

import com.spring.huntersleague.domain.Participation;
import com.spring.huntersleague.web.vm.response.participation.ParticipationListVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipationListMapper {
    ParticipationListVM toViewModel(Participation participation);
}
