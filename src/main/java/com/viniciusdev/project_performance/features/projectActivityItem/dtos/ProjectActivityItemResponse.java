package com.viniciusdev.project_performance.features.projectActivityItem.dtos;

import com.viniciusdev.project_performance.features.projectActivity.dtos.ProjectActivityResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public record ProjectActivityItemResponse(UUID id, ProjectActivityResponse projectActivity, LocalDate conclusionDate, BigDecimal charge, BigDecimal cost) { }
