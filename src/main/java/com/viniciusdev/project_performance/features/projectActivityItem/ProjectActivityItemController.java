package com.viniciusdev.project_performance.features.projectActivityItem;

import com.viniciusdev.project_performance.features.projectActivityItem.dtos.ProjectActivityItemRequest;
import com.viniciusdev.project_performance.features.projectActivityItem.dtos.ProjectActivityItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/project-activity-item")
public class ProjectActivityItemController {

    @Autowired
    private ProjectActivityItemService service;

    @GetMapping
    public ResponseEntity<List<ProjectActivityItemResponse>> findAll() {
        return ResponseEntity
                .ok()
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectActivityItemResponse> findById(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectActivityItemResponse> create(@RequestBody ProjectActivityItemRequest projectActivityItemRequest, UriComponentsBuilder uriBuilder) {

        ProjectActivityItemResponse object = service.create(projectActivityItemRequest);

        URI location = uriBuilder
                .path("/project-activity-item/{id}")
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
    public ResponseEntity<ProjectActivityItemResponse> update(@PathVariable UUID id, @RequestBody ProjectActivityItemRequest projectActivityItemRequest) {
        return ResponseEntity.ok()
                .body(service.update(projectActivityItemRequest, id));
    }

}
