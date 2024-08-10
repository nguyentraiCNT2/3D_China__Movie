package org.project4.backend.repository.user_repository;

import org.project4.backend.entity.Movie_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Home_Repository extends JpaRepository<Movie_Entity, Long> {
    List<Movie_Entity> findByNewmovie(Boolean new_movie, Pageable pageable);
    List<Movie_Entity> findByHotmovie(Boolean hot_movie, Pageable pageable);
    @Query("select m from Movie_Entity m  order by m.totalviews desc  limit 10")
    List<Movie_Entity> findByTotalviews();
}
