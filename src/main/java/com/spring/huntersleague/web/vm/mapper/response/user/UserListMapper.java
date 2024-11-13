package com.spring.huntersleague.web.vm.mapper.response.user;

import com.spring.huntersleague.domain.User;

import com.spring.huntersleague.web.vm.response.user.UserListVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserListMapper {
    UserListVM toViewModel(User user);
}
