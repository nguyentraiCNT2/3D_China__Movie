package org.project4.backend.service.admin_service;

import org.project4.backend.dto.Schedule_Movie_DTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface Schedule_movie_Service {
    List<Schedule_Movie_DTO> findAll();
    List<Schedule_Movie_DTO> findBymovie(Long scheduleId);
    List<Schedule_Movie_DTO> getBySchedule(Long scheduleId);
}
