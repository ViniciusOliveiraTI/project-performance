package com.viniciusdev.project_performance.dtos;

import com.viniciusdev.project_performance.entities.Proposal;
import com.viniciusdev.project_performance.enums.ProjectStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjectResponse(Long id, ProjectStatus status, BigDecimal approvedPrice, LocalDate approvalDate, Proposal proposal) { }
