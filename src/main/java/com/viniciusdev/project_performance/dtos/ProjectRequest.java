package com.viniciusdev.project_performance.dtos;

import com.viniciusdev.project_performance.enums.ProjectStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjectRequest(ProjectStatus status, BigDecimal approvedPrice, LocalDate approvalDate, Long proposalId) { }
