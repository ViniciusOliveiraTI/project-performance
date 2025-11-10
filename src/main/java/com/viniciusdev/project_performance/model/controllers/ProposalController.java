package com.viniciusdev.project_performance.model.controllers;

import com.viniciusdev.project_performance.model.entities.Proposal;
import com.viniciusdev.project_performance.model.services.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    @Autowired
    private ProposalService service;

    @GetMapping
    public List<Proposal> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Proposal findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

}
