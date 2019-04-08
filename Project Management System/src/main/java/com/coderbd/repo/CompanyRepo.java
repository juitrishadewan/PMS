package com.coderbd.repo;

import com.coderbd.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {
    Company findByEmail(String email);
    boolean existsCompanyByEmail(String email);
}
