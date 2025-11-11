package com.viniciusdev.project_performance.controllers;

import com.viniciusdev.project_performance.dtos.ProposalRequest;
import com.viniciusdev.project_performance.dtos.ProposalResponse;
import com.viniciusdev.project_performance.services.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    @Autowired
    private ProposalService service;

    @GetMapping
    public ResponseEntity<List<ProposalResponse>> findAll() {
        return ResponseEntity
                .ok()
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalResponse> findById(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProposalResponse> create(@RequestBody ProposalRequest proposalRequest, UriComponentsBuilder uriBuilder) {

        ProposalResponse object = service.create(proposalRequest);

        URI location = uriBuilder
                .path("/proposal/{id}")
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
    public ResponseEntity<ProposalResponse> update(@PathVariable Long id, @RequestBody ProposalRequest proposalRequest) {
        return ResponseEntity.ok()
                .body(service.update(proposalRequest, id));
    }

}
