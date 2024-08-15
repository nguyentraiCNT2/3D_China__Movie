package org.project4.backend.controller.api.admin;

import org.project4.backend.dto.Schedule_Movie_DTO;
import org.project4.backend.service.admin_service.Schedule_movie_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/schedule-movie")
public class Schedule_Movie_Controller {
    @Autowired
    private Schedule_movie_Service scheduleMovieService;
    @GetMapping("/getbymovie/{id}")
    public List<Schedule_Movie_DTO> getByMovie_id(@PathVariable Long id) {
        List<Schedule_Movie_DTO> result = scheduleMovieService.findBymovie(id);
        return result;
    }
    @GetMapping("/getbyschedule/{id}")
    public List<Schedule_Movie_DTO> getbyschedule(@PathVariable Long id) {
        List<Schedule_Movie_DTO> result = scheduleMovieService.getBySchedule(id);
        return result;
    }
    @GetMapping("/all")
    public List<Schedule_Movie_DTO> getall() {
        List<Schedule_Movie_DTO> result = scheduleMovieService.findAll();
        return result;
    }


}
