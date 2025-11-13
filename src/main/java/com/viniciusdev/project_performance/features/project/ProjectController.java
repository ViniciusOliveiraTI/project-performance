package com.viniciusdev.project_performance.features.project;

import com.viniciusdev.project_performance.features.project.dtos.ProjectRequest;
import com.viniciusdev.project_performance.features.project.dtos.ProjectResponse;
import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityResponse;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> findAll() {
        return ResponseEntity
                .ok()
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> findById(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@RequestBody ProjectRequest projectRequest, UriComponentsBuilder uriBuilder) {

        ProjectResponse object = service.create(projectRequest);

        URI location = uriBuilder
                .path("/project/{id}")
                .buildAndExpand(object.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(object);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(@PathVariable UUID id, @RequestBody ProjectRequest projectRequest) {
        return ResponseEntity.ok()
                .body(service.update(projectRequest, id));
    }

    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ProjectActivityResponse>> findAllActivities(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findAllActivities(id));
    }

    @GetMapping("/{id}/proposal")
    public ResponseEntity<ProposalResponse> findProposal(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findProposal(id));
    }

}
