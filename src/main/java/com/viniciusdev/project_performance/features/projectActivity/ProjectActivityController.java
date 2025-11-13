package com.viniciusdev.project_performance.features.projectActivity;

import com.viniciusdev.project_performance.features.project.dtos.ProjectResponse;
import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityRequest;
import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityResponse;
import com.viniciusdev.project_performance.features.projectActivityItem.dtos.ProjectActivityItemResponse;
import com.viniciusdev.project_performance.features.projectActivityItem.entities.ProjectActivityItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/project-activity")
public class ProjectActivityController {

    @Autowired
    private ProjectActivityService service;

    @GetMapping
    public ResponseEntity<List<ProjectActivityResponse>> findAll() {
        return ResponseEntity
                .ok()
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectActivityResponse> findById(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectActivityResponse> create(@RequestBody ProjectActivityRequest projectRequest, UriComponentsBuilder uriBuilder) {

        ProjectActivityResponse object = service.create(projectRequest);

        URI location = uriBuilder
                .path("/project-activity/{id}")
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
    public ResponseEntity<ProjectActivityResponse> update(@PathVariable UUID id, @RequestBody ProjectActivityRequest projectRequest) {
        return ResponseEntity.ok()
                .body(service.update(projectRequest, id));
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<ProjectActivityItemResponse>> findAllItems(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findAllItems(id));
    }

    @GetMapping("/{id}/project")
    public ResponseEntity<ProjectResponse> findProject(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findProject(id));
    }

}
