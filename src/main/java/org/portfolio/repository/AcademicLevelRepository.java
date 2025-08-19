package org.portfolio.repository;

import org.portfolio.model.entity.AcademicLevel;
import org.portfolio.model.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicLevelRepository extends JpaRepository<AcademicLevel, Long> {
    Page<AcademicLevel> findAll(Pageable pageable);
}

