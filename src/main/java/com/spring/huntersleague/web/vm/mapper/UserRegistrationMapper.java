package com.spring.huntersleague.web.vm.mapper;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.web.vm.UserRegisterVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {

    User toEntity(UserRegisterVM userRegistrationVM);
    UserRegisterVM toDto(User user);
}
