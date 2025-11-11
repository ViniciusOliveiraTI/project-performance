package com.viniciusdev.project_performance.features.proposal;

import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {}
