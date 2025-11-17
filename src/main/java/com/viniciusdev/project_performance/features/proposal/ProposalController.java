package com.viniciusdev.project_performance.features.proposal;

import com.viniciusdev.project_performance.features.customer.dtos.CustomerResponse;
import com.viniciusdev.project_performance.features.project.dtos.ProjectResponse;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalRequest;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationResponse;
import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    @Autowired
    private ProposalService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProposalResponse>> findAll() {
        return ResponseEntity
                .ok()
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalResponse> findById(@PathVariable UUID id) {
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
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProposalResponse> update(@PathVariable UUID id, @RequestBody ProposalRequest proposalRequest) {
        return ResponseEntity.ok()
                .body(service.update(proposalRequest, id));
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<ProjectResponse>> findAllProjects(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findAllProjects(id));
    }

    @GetMapping("/{id}/quotations")
    public ResponseEntity<List<ProposalQuotationResponse>> findAllProposalQuotation(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findAllProposalQuotation(id));
    }

    @GetMapping("/{id}/customer")
    public ResponseEntity<CustomerResponse> findCustomer(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findCustomer(id));
    }
}
