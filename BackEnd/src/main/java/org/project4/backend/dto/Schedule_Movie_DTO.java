package org.project4.backend.dto;

public class Schedule_Movie_DTO {
    private Long id;

    private Movie_DTO movie;

    private Schedule_DTO schedule;

    public Movie_DTO getMovie() {
        return movie;
    }

    public void setMovie(Movie_DTO movie) {
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Schedule_DTO getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule_DTO schedule) {
        this.schedule = schedule;
    }
}
