package com.viniciusdev.project_performance.features.projectActivity.dtos;

import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivityStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectActivityRequest(UUID projectId, String description, LocalDate expectedStartDate, LocalDateTime expectedEndDate, ProjectActivityStatus status) { }
