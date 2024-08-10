package org.project4.backend.repository.user_repository;

import org.project4.backend.entity.Movie_Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Movie_User_repository extends JpaRepository<Movie_Entity, Long> {

    @Query("SELECT m FROM Movie_Entity m " +
            "JOIN Category_Movie_Entity c ON c.movie.id = m.id " +
            "WHERE (:genre IS NULL OR c.category.id = :genre) " +
            "AND (:year IS NULL OR m.year = :year) " +
            "AND (:vip IS NULL OR m.vipmovie = :vip) " +
            "ORDER BY " +
            "CASE WHEN :sortBy IS null or :sortBy = 'date' THEN m.timeadd END DESC , " +
            "CASE WHEN :sortBy IS null or  :sortBy = 'views' THEN m.totalviews END DESC, " +
            "CASE WHEN :sortBy IS null or  :sortBy = 'name' THEN m.vnname END ASC")
    Page<Movie_Entity> findMovies(
            @Param("genre") Long genre,
            @Param("year") Long year,
            @Param("vip") Boolean vip,
            @Param("sortBy") String sortBy,
            Pageable pageable
    );

}
