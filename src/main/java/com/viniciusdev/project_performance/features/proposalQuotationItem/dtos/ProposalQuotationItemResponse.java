package com.viniciusdev.project_performance.features.proposalQuotationItem.dtos;

import com.viniciusdev.project_performance.features.proposalQuotation.dtos.ProposalQuotationResponse;
import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotation;

import java.math.BigDecimal;
import java.util.UUID;

public record ProposalQuotationItemResponse(UUID id, BigDecimal price, BigDecimal charge, ProposalQuotationResponse proposalQuotation) { }
