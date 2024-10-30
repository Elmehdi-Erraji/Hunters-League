package com.spring.huntersleague.web.vm.mapper;

import com.spring.huntersleague.domain.Hunt;
import com.spring.huntersleague.web.vm.HuntCreateVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HuntCreateMapper {

    @Mapping(target = "species.id", source = "speciesId")
    @Mapping(target = "participation.id", source = "participationId")
    Hunt toEntity(HuntCreateVM huntCreateVM);
}
