package com.viniciusdev.project_performance.features.proposal.dtos;

import com.viniciusdev.project_performance.features.customer.entities.Customer;
import com.viniciusdev.project_performance.features.proposal.entities.ProposalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProposalResponse(Long id, Integer code, String description, LocalDate emissionDate, ProposalStatus status, BigDecimal offeredPrice, Customer customer) { }
