package com.viniciusdev.project_performance.features.projectActivityItem.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProjectActivityItemRequest(Long activityId, LocalDate conclusionDate, BigDecimal charge, BigDecimal cost) { }
