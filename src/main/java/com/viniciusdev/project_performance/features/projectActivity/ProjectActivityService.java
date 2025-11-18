package com.viniciusdev.project_performance.features.projectActivity;

import com.viniciusdev.project_performance.app.exception.NotFoundException;
import com.viniciusdev.project_performance.features.project.ProjectMapper;
import com.viniciusdev.project_performance.features.project.ProjectRepository;
import com.viniciusdev.project_performance.features.project.dtos.ProjectResponse;
import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityRequest;
import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityResponse;
import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivity;
import com.viniciusdev.project_performance.features.projectActivityItem.ProjectActivityItemMapper;
import com.viniciusdev.project_performance.features.projectActivityItem.dtos.ProjectActivityItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectActivityService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectActivityRepository projectActivityRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectActivityMapper projectActivityMapper;

    @Autowired
    private ProjectActivityItemMapper projectActivityItemMapper;

    public List<ProjectActivityResponse> findAll() {
        return projectActivityRepository.findAll()
                .stream()
                .map(projectActivityMapper::entityToResponse).toList();
    }

    public ProjectActivityResponse findById(UUID id) {
        return projectActivityMapper.entityToResponse(
                projectActivityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project Activity with id '%s' not found".formatted(id))));
    }

    public ProjectActivityResponse create(ProjectActivityRequest projectActivityRequest) {

        Project project = projectRepository
                .findById(projectActivityRequest.projectId())
                .orElseThrow(() -> new NotFoundException("Project with id '%s' not found".formatted(projectActivityRequest.projectId())));

        ProjectActivity projectActivity = projectActivityMapper.requestToEntity(projectActivityRequest);

        projectActivity.setProject(project);

        ProjectActivity saved = projectActivityRepository.save(projectActivity);

        return projectActivityMapper.entityToResponse(saved);

    }

    public void deleteById(UUID id) {
        if (!projectActivityRepository.existsById(id)) {
            throw new NotFoundException("Project Activity with id '%s' not found".formatted(id));
        };
        try {
            projectActivityRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new NotFoundException("Project Activity with id '%s' cannot be deleted due to relations".formatted(id));
        }
    }

    public ProjectActivityResponse update(ProjectActivityRequest projectActivityRequest, UUID id) {

        ProjectActivity projectActivity = projectActivityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project Activity with id '%s' not found".formatted(id)));

        projectActivityMapper.updateEntityFromRequest(projectActivity, projectActivityRequest);

        Project project = projectRepository.findById(projectActivityRequest.projectId())
                .orElseThrow(() -> new NotFoundException("Project with id '%s' not found".formatted(projectActivityRequest.projectId())));

        projectActivity.setProject(project);

        return projectActivityMapper.entityToResponse(projectActivityRepository.save(projectActivity));
    }

    public List<ProjectActivityItemResponse> findAllItems(UUID id) {

        ProjectActivity projectActivity = projectActivityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project Activity with id '%s' cannot be deleted due to relations".formatted(id)));

        return projectActivity.getItems()
                .stream()
                .map(projectActivityItemMapper::entityToResponse)
                .toList();

    }

    public ProjectResponse findProject(UUID id) {

        ProjectActivity projectActivity = projectActivityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project Activity with id '%s' cannot be deleted due to relations".formatted(id)));

        return projectMapper.entityToResponse(projectActivity.getProject());

    }
}
