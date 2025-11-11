package com.viniciusdev.project_performance.dtos;

import com.viniciusdev.project_performance.entities.Customer;
import com.viniciusdev.project_performance.enums.ProposalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProposalResponse(Long id, Integer code, String description, LocalDate emissionDate, ProposalStatus status, BigDecimal offeredPrice, Customer customer) { }
