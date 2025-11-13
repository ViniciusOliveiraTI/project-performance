package com.viniciusdev.project_performance.features.projectActivityItem;

import com.viniciusdev.project_performance.common.exception.DataIntegrityException;
import com.viniciusdev.project_performance.common.exception.NotFoundException;
import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivity;
import com.viniciusdev.project_performance.features.projectActivity.ProjectActivityRepository;
import com.viniciusdev.project_performance.features.projectActivityItem.dtos.ProjectActivityItemRequest;
import com.viniciusdev.project_performance.features.projectActivityItem.dtos.ProjectActivityItemResponse;
import com.viniciusdev.project_performance.features.projectActivityItem.entities.ProjectActivityItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectActivityItemService {

    @Autowired
    private ProjectActivityRepository projectActivityRepository;

    @Autowired
    private ProjectActivityItemRepository projectActivityItemRepository;

    @Autowired
    private ProjectActivityItemMapper mapper;

    public List<ProjectActivityItemResponse> findAll() {
        return projectActivityItemRepository.findAll()
                .stream()
                .map(mapper::entityToResponse).toList();
    }

    public ProjectActivityItemResponse findById(UUID id) {
        return mapper.entityToResponse(
                projectActivityItemRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Project Activity Item with id '%s' not found".formatted(id))));
    }

    public ProjectActivityItemResponse create(ProjectActivityItemRequest projectActivityItemRequest) {

        ProjectActivity projectActivity = projectActivityRepository
                .findById(projectActivityItemRequest.activityId())
                .orElseThrow(() -> new NotFoundException("Project Activity with id '%s' not found".formatted(projectActivityItemRequest.activityId())));

        ProjectActivityItem projectActivityItem = mapper.requestToEntity(projectActivityItemRequest);

        projectActivityItem.setProjectActivity(projectActivity);

        ProjectActivityItem saved = projectActivityItemRepository.save(projectActivityItem);

        return mapper.entityToResponse(saved);

    }

    public void deleteById(UUID id) {
        if (!projectActivityItemRepository.existsById(id)) {
            throw new NotFoundException("Project Activity Item with id '%s' not found".formatted(id));
        };
        try {
            projectActivityItemRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Project Activity Item with id '%s' cannot be deleted due to relations".formatted(id));
        }
    }

    public ProjectActivityItemResponse update(ProjectActivityItemRequest projectActivityItemRequest, UUID id) {

        ProjectActivityItem projectActivityItem = projectActivityItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project Activity Item with id '%s' not found".formatted(id)));

        mapper.updateEntityFromRequest(projectActivityItem, projectActivityItemRequest);

        ProjectActivity projectActivity = projectActivityRepository.findById(projectActivityItemRequest.activityId())
                .orElseThrow(() -> new NotFoundException("Project Activity with id '%s' not found".formatted(projectActivityItemRequest.activityId())));

        projectActivityItem.setProjectActivity(projectActivity);

        return mapper.entityToResponse(projectActivityItemRepository.save(projectActivityItem));
    }
}
