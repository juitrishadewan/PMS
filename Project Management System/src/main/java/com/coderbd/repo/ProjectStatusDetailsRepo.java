package com.coderbd.repo;

import com.coderbd.entity.ProjectStatusDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStatusDetailsRepo extends JpaRepository<ProjectStatusDetails,Long> {
}
