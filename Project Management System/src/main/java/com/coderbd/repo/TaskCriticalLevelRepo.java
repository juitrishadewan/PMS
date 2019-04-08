package com.coderbd.repo;

import com.coderbd.entity.TaskCriticalLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCriticalLevelRepo extends JpaRepository<TaskCriticalLevel, Long> {
}
