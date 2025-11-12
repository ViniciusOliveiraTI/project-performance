package com.viniciusdev.project_performance.features.project;

import com.viniciusdev.project_performance.common.exception.NotFoundException;
import com.viniciusdev.project_performance.features.project.dtos.ProjectRequest;
import com.viniciusdev.project_performance.features.project.dtos.ProjectResponse;
import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.proposal.ProposalRepository;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProjectRepository projectrepository;

    @Autowired
    private ProjectMapper mapper;

    public List<ProjectResponse> findAll() {
        return projectrepository.findAll()
                .stream()
                .map(mapper::entityToResponse).toList();
    }

    public ProjectResponse findById(Long id) {
        return mapper.entityToResponse(
                projectrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project with id '%d' not found".formatted(id))));
    }

    public ProjectResponse create(ProjectRequest projectRequest) {

        Proposal proposal = proposalRepository
                .findById(projectRequest.proposalId())
                .orElseThrow(() -> new NotFoundException("Proposal with id '%d' not found".formatted(projectRequest.proposalId())));

        Project project = mapper.requestToEntity(projectRequest);

        project.setProposal(proposal);

        Project saved = projectrepository.save(project);

        return mapper.entityToResponse(saved);

    }

    public void deleteById(Long id) {
        if (!projectrepository.existsById(id)) {
            throw new NotFoundException("Project with id '%d' not found".formatted(id));
        };
        try {
            projectrepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new NotFoundException("Project with id '%d' cannot be deleted due to relations".formatted(id));
        }
    }

    public ProjectResponse update(ProjectRequest projectRequest, Long id) {

        Project project = projectrepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project with id '%d' not found".formatted(id)));

        mapper.updateEntityFromRequest(project, projectRequest);

        Proposal proposal = proposalRepository.findById(projectRequest.proposalId())
                .orElseThrow(() -> new NotFoundException("Proposal with id '%d' not found".formatted(projectRequest.proposalId())));

        project.setProposal(proposal);

        return mapper.entityToResponse(projectrepository.save(project));
    }
}
