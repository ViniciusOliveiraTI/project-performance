package com.viniciusdev.project_performance.features.user;

import com.viniciusdev.project_performance.features.user.dtos.UserRequest;
import com.viniciusdev.project_performance.features.user.dtos.UserResponse;
import com.viniciusdev.project_performance.features.user.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User requestToEntity(UserRequest userRequest);
    UserResponse entityToResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateEntityFromRequest(@MappingTarget User user, UserRequest userRequest);
}

