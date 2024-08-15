package org.project4.backend.repository.user_repository;

import org.project4.backend.entity.Comment_Movie_Entity;
import org.project4.backend.entity.Movie_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Comment_Movie_Repository extends JpaRepository<Comment_Movie_Entity, Long> {
    List<Comment_Movie_Entity> findByMovie(Movie_Entity movieid, Pageable pageable);
}
