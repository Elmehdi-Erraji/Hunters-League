package com.spring.huntersleague.web.vm.mapper.request.participation;

import com.spring.huntersleague.domain.Participation;
import com.spring.huntersleague.web.vm.request.participation.ParticipationUpdateVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipationUpdateMapper {

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "competition.id", source = "competitionId")
    Participation toEntity(ParticipationUpdateVM participationUpdateVM);
}
