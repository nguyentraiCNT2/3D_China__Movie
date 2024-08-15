package org.project4.backend.repository.user_repository;

import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.User_Entity;
import org.project4.backend.entity.User_Ratings_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ratings_Repository extends JpaRepository<User_Ratings_Entity, Long> {
    List<User_Ratings_Entity> findByUser(User_Entity userId, Pageable pageable);
    List<User_Ratings_Entity> findByMovie(Movie_Entity movieId, Pageable pageable);

    User_Ratings_Entity findByUserAndMovie(User_Entity userId, Movie_Entity movieId);
}
