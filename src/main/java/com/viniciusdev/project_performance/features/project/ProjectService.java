package com.viniciusdev.project_performance.features.project;

import com.viniciusdev.project_performance.common.exception.NotFoundException;
import com.viniciusdev.project_performance.features.project.dtos.ProjectRequest;
import com.viniciusdev.project_performance.features.project.dtos.ProjectResponse;
import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.projectActivity.ProjectActivityMapper;
import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityResponse;
import com.viniciusdev.project_performance.features.proposal.ProposalMapper;
import com.viniciusdev.project_performance.features.proposal.ProposalRepository;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProjectRepository projectrepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProposalMapper proposalMapper;

    @Autowired
    private ProjectActivityMapper projectActivityMapper;

    public List<ProjectResponse> findAll() {
        return projectrepository.findAll()
                .stream()
                .map(projectMapper::entityToResponse).toList();
    }

    public ProjectResponse findById(UUID id) {
        return projectMapper.entityToResponse(
                projectrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project with id '%s' not found".formatted(id))));
    }

    public ProjectResponse create(ProjectRequest projectRequest) {

        Proposal proposal = proposalRepository
                .findById(projectRequest.proposalId())
                .orElseThrow(() -> new NotFoundException("Proposal with id '%s' not found".formatted(projectRequest.proposalId())));

        Project project = projectMapper.requestToEntity(projectRequest);

        project.setProposal(proposal);

        Project saved = projectrepository.save(project);

        return projectMapper.entityToResponse(saved);

    }

    public void deleteById(UUID id) {
        if (!projectrepository.existsById(id)) {
            throw new NotFoundException("Project with id '%s' not found".formatted(id));
        };
        try {
            projectrepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new NotFoundException("Project with id '%s' cannot be deleted due to relations".formatted(id));
        }
    }

    public ProjectResponse update(ProjectRequest projectRequest, UUID id) {

        Project project = projectrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project with id '%s' not found".formatted(id)));

        projectMapper.updateEntityFromRequest(project, projectRequest);

        Proposal proposal = proposalRepository.findById(projectRequest.proposalId())
                .orElseThrow(() -> new NotFoundException("Proposal with id '%s' not found".formatted(projectRequest.proposalId())));

        project.setProposal(proposal);

        return projectMapper.entityToResponse(projectrepository.save(project));
    }

    public List<ProjectActivityResponse> findAllActivities(UUID id) {

        Project project = projectrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project with id '%s' not found".formatted(id)));

        return project.getActivities()
                .stream()
                .map(projectActivityMapper::entityToResponse)
                .toList();

    }

    public ProposalResponse findProposal(UUID id) {

        Project project = projectrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project with id '%s' not found".formatted(id)));

        return proposalMapper.entityToResponse(project.getProposal());
    }
}
