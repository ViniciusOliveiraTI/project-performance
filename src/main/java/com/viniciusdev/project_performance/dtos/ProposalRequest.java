package com.viniciusdev.project_performance.dtos;

import com.viniciusdev.project_performance.entities.Customer;
import com.viniciusdev.project_performance.enums.ProposalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProposalRequest(Integer code, String description, LocalDate emissionDate, ProposalStatus status, BigDecimal offeredPrice, Long customerId) {}
