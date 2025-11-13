package com.viniciusdev.project_performance.features.projectActivity;

import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectActivityRepository extends JpaRepository<ProjectActivity, Long> {}
