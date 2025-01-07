package com.spring.huntersleague.web.vm.response.user;

import com.spring.huntersleague.domain.User;
import com.spring.huntersleague.service.dto.UserRegistrationDTO;
import com.spring.huntersleague.web.vm.response.profile.UserProfileVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Map User entity to UserProfileDTO
    UserProfileVM toUserProfileDTO(User user);

    // Map UserRegistrationDTO to User entity
    @Mapping(target = "password", ignore = true) // Password should be set explicitly after hashing
    @Mapping(target = "role", expression = "java(com.spring.huntersleague.domain.enums.Role.MEMBER)") // Default role
    @Mapping(target = "joinDate", expression = "java(java.time.LocalDateTime.now())") // Default join date
    User toUser(UserRegistrationDTO dto);
}
