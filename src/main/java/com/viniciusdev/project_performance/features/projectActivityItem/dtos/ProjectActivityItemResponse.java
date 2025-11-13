package com.viniciusdev.project_performance.features.projectActivityItem.dtos;

import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivity;
import java.math.BigDecimal;
import java.time.LocalDate;


public record ProjectActivityItemResponse(Long id, ProjectActivity projectActivity, LocalDate conclusionDate, BigDecimal charge, BigDecimal cost) { }
