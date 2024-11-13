package com.spring.huntersleague.web.vm.mapper.request.user;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.web.vm.request.user.UserLoginVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserLoginMapper {
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    User toEntity(UserLoginVM userLoginVM);
    UserLoginVM toDto(User user);
}