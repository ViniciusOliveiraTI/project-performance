package com.viniciusdev.project_performance.features.projectActivityItem.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectActivityItemRequest(UUID activityId, LocalDate conclusionDate, BigDecimal charge, BigDecimal cost) { }
