package com.viniciusdev.project_performance.features.project.dtos;

import com.viniciusdev.project_performance.features.project.entities.ProjectStatus;
import com.viniciusdev.project_performance.features.proposal.dtos.ProposalResponse;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProjectResponse(UUID id, ProjectStatus status, BigDecimal approvedPrice, LocalDate approvalDate, ProposalResponse proposal) { }
