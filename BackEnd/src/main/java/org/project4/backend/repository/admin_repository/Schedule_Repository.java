package org.project4.backend.repository.admin_repository;

import org.project4.backend.entity.Schedule_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Schedule_Repository extends JpaRepository<Schedule_Entity, Long> {
    List<Schedule_Entity> findByNameLike(String name, Pageable pageable);
}
