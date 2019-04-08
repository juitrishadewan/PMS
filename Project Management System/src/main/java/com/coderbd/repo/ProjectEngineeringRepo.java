package com.coderbd.repo;

import com.coderbd.entity.ProjectEngineering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectEngineeringRepo extends JpaRepository<ProjectEngineering, Long> {
}
