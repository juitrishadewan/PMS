package com.coderbd.repo;

import com.coderbd.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStatusRepo extends JpaRepository<ProjectStatus, Long> {
}
