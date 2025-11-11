package com.viniciusdev.project_performance.mappers;

import com.viniciusdev.project_performance.dtos.ProjectRequest;
import com.viniciusdev.project_performance.dtos.ProjectResponse;
import com.viniciusdev.project_performance.entities.Project;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project requestToEntity(ProjectRequest projectRequest);
    ProjectResponse entityToResponse(Project project);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateEntityFromRequest(@MappingTarget Project project, ProjectRequest projectRequest);
}

