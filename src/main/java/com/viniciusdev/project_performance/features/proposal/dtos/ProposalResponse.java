package com.viniciusdev.project_performance.features.proposal.dtos;

import com.viniciusdev.project_performance.features.customer.dtos.CustomerResponse;
import com.viniciusdev.project_performance.features.customer.entities.Customer;
import com.viniciusdev.project_performance.features.proposal.entities.ProposalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProposalResponse(UUID id, Integer code, String description, LocalDate emissionDate, ProposalStatus status, BigDecimal offeredPrice, CustomerResponse customer) { }
