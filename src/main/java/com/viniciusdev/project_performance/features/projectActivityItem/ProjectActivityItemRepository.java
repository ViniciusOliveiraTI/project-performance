package com.viniciusdev.project_performance.features.projectActivityItem;

import com.viniciusdev.project_performance.features.projectActivityItem.entities.ProjectActivityItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectActivityItemRepository extends JpaRepository<ProjectActivityItem, Long> {}
