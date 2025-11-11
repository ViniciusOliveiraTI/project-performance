package com.viniciusdev.project_performance.features.proposal.dtos;

import com.viniciusdev.project_performance.features.proposal.entities.ProposalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProposalRequest(Integer code, String description, LocalDate emissionDate, ProposalStatus status, BigDecimal offeredPrice, Long customerId) {}
