package com.viniciusdev.project_performance.features.project.dtos;

import com.viniciusdev.project_performance.features.project.entities.ProjectStatus;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjectResponse(Long id, ProjectStatus status, BigDecimal approvedPrice, LocalDate approvalDate, Proposal proposal) { }
