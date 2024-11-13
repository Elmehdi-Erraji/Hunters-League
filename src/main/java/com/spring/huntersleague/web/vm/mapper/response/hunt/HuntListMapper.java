package com.spring.huntersleague.web.vm.mapper.response.hunt;

import com.spring.huntersleague.domain.Hunt;
import com.spring.huntersleague.web.vm.response.hunt.HuntListVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HuntListMapper {
    HuntListVM toViewModel(Hunt hunt);
}
