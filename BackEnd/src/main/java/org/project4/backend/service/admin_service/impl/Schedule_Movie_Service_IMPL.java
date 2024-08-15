package org.project4.backend.service.admin_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Schedule_DTO;
import org.project4.backend.dto.Schedule_Movie_DTO;
import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.Schedule_Entity;
import org.project4.backend.entity.Schedule_Movie_Entity;
import org.project4.backend.repository.admin_repository.Movie_Repository;
import org.project4.backend.repository.admin_repository.Schedule_Movie_Repository;
import org.project4.backend.repository.admin_repository.Schedule_Repository;
import org.project4.backend.service.admin_service.Schedule_Service;
import org.project4.backend.service.admin_service.Schedule_movie_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Schedule_Movie_Service_IMPL implements Schedule_movie_Service {
    @Autowired
    private Schedule_Movie_Repository scheduleMovieRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Movie_Repository movieRepository;
    @Autowired
    private Schedule_Repository scheduleRepository;
    @Override
    public List<Schedule_Movie_DTO> findAll() {
        List<Schedule_Movie_DTO> result = new ArrayList<>();
        List<Schedule_Movie_Entity> scheduleMovieEntities = scheduleMovieRepository.findAll();
        if (scheduleMovieEntities == null) {
            throw new RuntimeException("Không có bộ phim này");
        }
        for (Schedule_Movie_Entity scheduleMovieEntity : scheduleMovieEntities) {
            Schedule_Movie_DTO dto = modelMapper.map(scheduleMovieEntity, Schedule_Movie_DTO.class);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Schedule_Movie_DTO> findBymovie(Long scheduleId) {
        List<Schedule_Movie_DTO> result = new ArrayList<>();
        Movie_Entity movie = movieRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Không có bộ phim này"));
        List<Schedule_Movie_Entity> scheduleMovieEntities = scheduleMovieRepository.findByMovie(movie);
        if (scheduleMovieEntities == null) {
         throw new RuntimeException("Không có bộ phim này");
        }
        for (Schedule_Movie_Entity scheduleMovieEntity : scheduleMovieEntities) {
            Schedule_Movie_DTO dto = modelMapper.map(scheduleMovieEntity, Schedule_Movie_DTO.class);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Schedule_Movie_DTO> getBySchedule(Long scheduleId) {
        List<Schedule_Movie_DTO> result = new ArrayList<>();
        Schedule_Entity schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Không có bộ phim này"));
        List<Schedule_Movie_Entity> scheduleMovieEntities = scheduleMovieRepository.findBySchedule(schedule);
        if (scheduleMovieEntities == null) {
            throw new RuntimeException("Không có bộ phim này");
        }
        for (Schedule_Movie_Entity scheduleMovieEntity : scheduleMovieEntities) {
            Schedule_Movie_DTO dto = modelMapper.map(scheduleMovieEntity, Schedule_Movie_DTO.class);
            result.add(dto);
        }
        return result;
    }
}
