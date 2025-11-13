package com.viniciusdev.project_performance.features.projectActivity.dtos;

import com.viniciusdev.project_performance.features.project.dtos.ProjectResponse;
import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivityStatus;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectActivityResponse(UUID id, String description, LocalDate expectedStartDate, LocalDateTime expectedEndDate, ProjectActivityStatus status, ProjectResponse project) { }
