package org.project4.backend.repository.admin_repository;

import org.project4.backend.entity.Movie_Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Static_Repository extends JpaRepository<Movie_Entity, Long> {
}
