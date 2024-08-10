package org.project4.backend.repository.admin_repository;

import org.project4.backend.entity.Episode_enitty;
import org.project4.backend.entity.Movie_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Episode_Repository extends JpaRepository<Episode_enitty, Long> {
    List<Episode_enitty> findByMovie(Movie_Entity movie, Pageable pageable);
    List<Episode_enitty> findByMovie(Movie_Entity movie);

}
