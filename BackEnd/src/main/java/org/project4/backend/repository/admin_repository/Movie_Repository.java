package org.project4.backend.repository.admin_repository;

import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.User_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface Movie_Repository extends JpaRepository<Movie_Entity, Long> {
    List<Movie_Entity> findByNewmovie(Boolean new_movie, Pageable pageable);
    List<Movie_Entity> findByHotmovie(Boolean hot_movie, Pageable pageable);
    List<Movie_Entity> findByAuthorLike(String author, Pageable pageable);
    List<Movie_Entity> findByVnnameLike(String vn_name, Pageable pageable);
    List<Movie_Entity> findByCnnameLike(String cn_name, Pageable pageable);
    List<Movie_Entity> findByUseradd(User_Entity user_add, Pageable pageable);
    List<Movie_Entity> findByUserupdate(User_Entity user_update, Pageable pageable);
    List<Movie_Entity> findByVipmovie(Boolean vip_movie, Pageable pageable);
    List<Movie_Entity> findByVipmovie(Boolean vip_movie);
    List<Movie_Entity> findByTimeadd(Date time_add, Pageable pageable);
    List<Movie_Entity> findByTimeupdate(Date time_update, Pageable pageable);
}
