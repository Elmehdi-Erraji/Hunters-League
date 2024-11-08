package com.spring.huntersleague.web.vm.mapper.request.user;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.web.vm.request.user.UserRegisterVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {

    User toEntity(UserRegisterVM userRegistrationVM);
    UserRegisterVM toDto(User user);
}
