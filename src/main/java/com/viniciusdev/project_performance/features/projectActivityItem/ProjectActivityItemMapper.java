package com.viniciusdev.project_performance.features.projectActivityItem;

import com.viniciusdev.project_performance.features.projectActivityItem.dtos.ProjectActivityItemRequest;
import com.viniciusdev.project_performance.features.projectActivityItem.dtos.ProjectActivityItemResponse;
import com.viniciusdev.project_performance.features.projectActivityItem.entities.ProjectActivityItem;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProjectActivityItemMapper {
    ProjectActivityItem requestToEntity(ProjectActivityItemRequest projectActivityItemRequest);
    ProjectActivityItemResponse entityToResponse(ProjectActivityItem projectActivityItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateEntityFromRequest(@MappingTarget ProjectActivityItem projectActivityItem, ProjectActivityItemRequest projectActivityItemRequest);
}