package org.project4.backend.repository.admin_repository;

import org.project4.backend.entity.Category_Entity;
import org.project4.backend.entity.Category_Movie_Entity;
import org.project4.backend.entity.Movie_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Category_Movie_Repository extends JpaRepository<Category_Movie_Entity, Long> {
    List<Category_Movie_Entity> findByCategory(Category_Entity category);
    List<Category_Movie_Entity> findByCategory(Category_Entity category, Pageable pageable);
    List<Category_Movie_Entity> findByMovie(Movie_Entity movie);
}
