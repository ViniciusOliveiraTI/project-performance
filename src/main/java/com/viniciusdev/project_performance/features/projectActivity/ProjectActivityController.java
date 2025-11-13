package com.viniciusdev.project_performance.features.projectActivity;

import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityRequest;
import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<ProjectActivityResponse> findById(@PathVariable Long id) {
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
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectActivityResponse> update(@PathVariable Long id, @RequestBody ProjectActivityRequest projectRequest) {
        return ResponseEntity.ok()
                .body(service.update(projectRequest, id));
    }

}
