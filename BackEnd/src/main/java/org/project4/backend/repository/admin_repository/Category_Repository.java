package org.project4.backend.repository.admin_repository;

import org.project4.backend.entity.Category_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Category_Repository extends JpaRepository<Category_Entity, Long> {
    List<Category_Entity> findByNameLike(String name, Pageable pageable);
}
