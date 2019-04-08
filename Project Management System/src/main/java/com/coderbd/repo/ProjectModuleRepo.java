package com.coderbd.repo;

import com.coderbd.entity.ProjectModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectModuleRepo extends JpaRepository<ProjectModule, Long> {
}
