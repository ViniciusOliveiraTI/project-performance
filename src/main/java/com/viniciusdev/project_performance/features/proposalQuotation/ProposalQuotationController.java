package com.viniciusdev.project_performance.features.proposalQuotation;

import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationRequest;
import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationResponse;
import com.viniciusdev.project_performance.features.proposalQuotationItem.dtos.ProposalQuotationItemResponse;
import com.viniciusdev.project_performance.features.proposalQuotationItem.entities.ProposalQuotationItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/proposal-quotation")
public class ProposalQuotationController {

    @Autowired
    private ProposalQuotationService service;

    @GetMapping
    public ResponseEntity<List<ProposalQuotationResponse>> findAll() {
        return ResponseEntity
                .ok()
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalQuotationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProposalQuotationResponse> create(@RequestBody ProposalQuotationRequest proposalQuotationRequest, UriComponentsBuilder uriBuilder) {

        ProposalQuotationResponse object = service.create(proposalQuotationRequest);

        URI location = uriBuilder
                .path("/proposal-quotation/{id}")
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
    public ResponseEntity<ProposalQuotationResponse> update(@PathVariable UUID id, @RequestBody ProposalQuotationRequest proposalQuotationRequest) {
        return ResponseEntity.ok()
                .body(service.update(proposalQuotationRequest, id));
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<ProposalQuotationItemResponse>> findAllProposalQuotationItem(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findAllProposalQuotationItem(id));
    }

    @GetMapping("/{id}/proposal")
    public ResponseEntity<ProposalResponse> findProposal(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findProposal(id));
    }

}
