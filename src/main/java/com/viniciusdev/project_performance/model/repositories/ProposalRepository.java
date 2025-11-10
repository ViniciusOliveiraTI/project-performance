package com.viniciusdev.project_performance.model.repositories;

import com.viniciusdev.project_performance.model.entities.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {}
