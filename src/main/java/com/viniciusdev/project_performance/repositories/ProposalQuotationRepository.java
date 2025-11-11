package com.viniciusdev.project_performance.repositories;

import com.viniciusdev.project_performance.entities.ProposalQuotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalQuotationRepository extends JpaRepository<ProposalQuotation, Long> {}
