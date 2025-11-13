package com.viniciusdev.project_performance.features.projectActivity;

import com.viniciusdev.project_performance.common.exception.NotFoundException;
import com.viniciusdev.project_performance.features.project.ProjectRepository;
import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityRequest;
import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityResponse;
import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectActivityService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectActivityRepository projectActivityRepository;

    @Autowired
    private ProjectActivityMapper mapper;

    public List<ProjectActivityResponse> findAll() {
        return projectActivityRepository.findAll()
                .stream()
                .map(mapper::entityToResponse).toList();
    }

    public ProjectActivityResponse findById(Long id) {
        return mapper.entityToResponse(
                projectActivityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project Activity with id '%d' not found".formatted(id))));
    }

    public ProjectActivityResponse create(ProjectActivityRequest projectActivityRequest) {

        Project project = projectRepository
                .findById(projectActivityRequest.projectId())
                .orElseThrow(() -> new NotFoundException("Project with id '%d' not found".formatted(projectActivityRequest.projectId())));

        ProjectActivity projectActivity = mapper.requestToEntity(projectActivityRequest);

        projectActivity.setProject(project);

        ProjectActivity saved = projectActivityRepository.save(projectActivity);

        return mapper.entityToResponse(saved);

    }

    public void deleteById(Long id) {
        if (!projectActivityRepository.existsById(id)) {
            throw new NotFoundException("Project Activity with id '%d' not found".formatted(id));
        };
        try {
            projectActivityRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new NotFoundException("Project Activity with id '%d' cannot be deleted due to relations".formatted(id));
        }
    }

    public ProjectActivityResponse update(ProjectActivityRequest projectActivityRequest, Long id) {

        ProjectActivity projectActivity = projectActivityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project Activity with id '%d' not found".formatted(id)));

        mapper.updateEntityFromRequest(projectActivity, projectActivityRequest);

        Project project = projectRepository.findById(projectActivityRequest.projectId())
                .orElseThrow(() -> new NotFoundException("Project with id '%d' not found".formatted(projectActivityRequest.projectId())));

        projectActivity.setProject(project);

        return mapper.entityToResponse(projectActivityRepository.save(projectActivity));
    }
}
