package com.spring.huntersleague.web.vm.mapper.request.user;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.domain.enums.Role;
import com.spring.huntersleague.web.vm.request.user.UserUpdateVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",imports = {Role.class})
public interface UserUpdateMapper {

    @Mapping(target = "role", expression = "java(Role.valueOf(userUpdateVM.getRole().toUpperCase()))")
    User toEntity(UserUpdateVM userUpdateVM);
}
