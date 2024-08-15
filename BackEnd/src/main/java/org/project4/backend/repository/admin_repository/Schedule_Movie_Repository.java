package org.project4.backend.repository.admin_repository;

import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.Schedule_Entity;
import org.project4.backend.entity.Schedule_Movie_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Schedule_Movie_Repository extends JpaRepository<Schedule_Movie_Entity, Long> {
    List<Schedule_Movie_Entity> findByMovie(Movie_Entity movieId);

    List<Schedule_Movie_Entity> findBySchedule(Schedule_Entity scheduleId);
    List<Schedule_Movie_Entity> findAllBySchedule(Schedule_Entity scheduleId);

}
