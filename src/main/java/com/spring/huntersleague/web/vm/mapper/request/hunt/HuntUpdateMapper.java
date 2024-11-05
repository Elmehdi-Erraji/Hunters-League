package com.spring.huntersleague.web.vm.mapper.request.hunt;

import com.spring.huntersleague.domain.Hunt;
import com.spring.huntersleague.web.vm.request.hunt.HuntUpdateVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HuntUpdateMapper {

    @Mapping(target = "species.id", source = "speciesId")
    @Mapping(target = "participation.id", source = "participationId")
    Hunt toEntity(HuntUpdateVM huntUpdateVM);
}
