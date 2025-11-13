package com.viniciusdev.project_performance.features.project.dtos;

import com.viniciusdev.project_performance.features.project.entities.ProjectStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProjectRequest(ProjectStatus status, BigDecimal approvedPrice, LocalDate approvalDate, UUID proposalId) { }
