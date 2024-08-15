package org.project4.backend.repository.user_repository;

import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.User_Entity;
import org.project4.backend.entity.User_Follow_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_Follow_Repository extends JpaRepository<User_Follow_Entity, Long> {
    List<User_Follow_Entity> findByUser(User_Entity userId, Pageable pageable);
    List<User_Follow_Entity> findByUserAndMovie(User_Entity userId, Movie_Entity movieId);
}
