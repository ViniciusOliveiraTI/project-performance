package com.viniciusdev.project_performance.features.project;

import com.viniciusdev.project_performance.features.project.dtos.ProjectRequest;
import com.viniciusdev.project_performance.features.project.dtos.ProjectResponse;
import com.viniciusdev.project_performance.features.project.entities.Project;
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

