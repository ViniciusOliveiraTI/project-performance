package com.viniciusdev.project_performance.features.customer;

import com.viniciusdev.project_performance.features.customer.dtos.CustomerRequest;
import com.viniciusdev.project_performance.features.customer.dtos.CustomerResponse;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity
                .ok()
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable UUID id) {
        return ResponseEntity
                .ok()
                .body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest customerRequest, UriComponentsBuilder uriBuilder) {

        CustomerResponse object = service.create(customerRequest);

        URI location = uriBuilder
                .path("/customer/{id}")
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
    public ResponseEntity<CustomerResponse> update(@PathVariable UUID id, @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok()
                .body(service.update(customerRequest, id));
    }

    @GetMapping("/{id}/proposals")
    public ResponseEntity<List<ProposalResponse>> findAllProposals(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findAllProposals(id));
    }

}
