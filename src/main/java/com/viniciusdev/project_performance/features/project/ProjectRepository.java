package com.viniciusdev.project_performance.features.project;

import com.viniciusdev.project_performance.features.project.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {}
