package com.viniciusdev.project_performance.repositories;

import com.viniciusdev.project_performance.entities.ProposalQuotationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalQuotationItemRepository extends JpaRepository<ProposalQuotationItem, Long> {}
