package com.viniciusdev.project_performance.features.proposalQuotationItem.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record ProposalQuotationItemRequest(BigDecimal price, BigDecimal charge, UUID proposalQuotationId) { }
