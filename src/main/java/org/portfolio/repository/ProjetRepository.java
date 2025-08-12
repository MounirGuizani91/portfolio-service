package org.portfolio.repository;

import org.portfolio.model.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
    Page<Projet> findAll(Pageable pageable);
}