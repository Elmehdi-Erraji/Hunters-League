package com.spring.huntersleague.web.vm.mapper;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.web.vm.UserRegisterVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRegistrationMapper {
    UserRegistrationMapper INSTANCE = Mappers.getMapper(UserRegistrationMapper.class);

    User toEntity(UserRegisterVM userRegistrationVM);
    UserRegisterVM toDto(User user);
}
