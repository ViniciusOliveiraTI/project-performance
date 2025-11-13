package com.viniciusdev.project_performance.features.proposalQuotationItem;

import com.viniciusdev.project_performance.features.proposalQuotationItem.entities.ProposalQuotationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProposalQuotationItemRepository extends JpaRepository<ProposalQuotationItem, UUID> {}
