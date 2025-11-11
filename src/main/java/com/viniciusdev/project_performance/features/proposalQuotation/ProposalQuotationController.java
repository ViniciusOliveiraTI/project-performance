package com.viniciusdev.project_performance.features.proposalQuotation;

import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationRequest;
import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<ProposalQuotationResponse> findById(@PathVariable Long id) {
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
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProposalQuotationResponse> update(@PathVariable Long id, @RequestBody ProposalQuotationRequest proposalQuotationRequest) {
        return ResponseEntity.ok()
                .body(service.update(proposalQuotationRequest, id));
    }

}
