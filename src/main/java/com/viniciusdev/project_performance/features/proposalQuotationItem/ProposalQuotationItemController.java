package com.viniciusdev.project_performance.features.proposalQuotationItem;

import com.viniciusdev.project_performance.features.proposalQuotationItem.dtos.ProposalQuotationItemRequest;
import com.viniciusdev.project_performance.features.proposalQuotationItem.dtos.ProposalQuotationItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/proposal-quotation-item")
public class ProposalQuotationItemController {

    @Autowired
    private ProposalQuotationItemService service;

    @GetMapping
    public ResponseEntity<List<ProposalQuotationItemResponse>> findAll() {
        return ResponseEntity
                .ok()
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalQuotationItemResponse> findById(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProposalQuotationItemResponse> create(@RequestBody ProposalQuotationItemRequest proposalQuotationItemRequest, UriComponentsBuilder uriBuilder) {

        ProposalQuotationItemResponse object = service.create(proposalQuotationItemRequest);

        URI location = uriBuilder
                .path("/proposal-quotation-item/{id}")
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
    public ResponseEntity<ProposalQuotationItemResponse> update(@PathVariable Long id, @RequestBody ProposalQuotationItemRequest proposalQuotationItemRequest) {
        return ResponseEntity.ok()
                .body(service.update(proposalQuotationItemRequest, id));
    }

}
