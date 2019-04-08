package com.coderbd.repo;

import com.coderbd.entity.AssignTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignTaskRepo extends JpaRepository<AssignTask, Long> {
}
