package com.viniciusdev.project_performance.repositories;

import com.viniciusdev.project_performance.entities.Project;
import com.viniciusdev.project_performance.entities.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {}
