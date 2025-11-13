package com.viniciusdev.project_performance.features.projectActivity;

import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityRequest;
import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityResponse;
import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProjectActivityMapper {
    ProjectActivity requestToEntity(ProjectActivityRequest projectActivityRequest);
    ProjectActivityResponse entityToResponse(ProjectActivity projectActivity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateEntityFromRequest(@MappingTarget ProjectActivity projectActivity, ProjectActivityRequest projectActivityRequest);
}

