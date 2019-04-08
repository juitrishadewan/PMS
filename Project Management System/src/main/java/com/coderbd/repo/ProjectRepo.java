package com.coderbd.repo;

import com.coderbd.entity.Company;
import com.coderbd.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long> {
    List<Project> findAllByCompany(Company company);
    boolean existsProjectByCompanyAndTitle(Company company, String title);
    Project findByCompanyAndTitle(Company company, String title);
}
