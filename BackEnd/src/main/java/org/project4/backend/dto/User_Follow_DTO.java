package org.project4.backend.dto;

import jakarta.persistence.*;

public class User_Follow_DTO {
    private Long id;
    private User_DTO user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie_DTO movie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User_DTO getUser() {
        return user;
    }

    public void setUser(User_DTO user) {
        this.user = user;
    }

    public Movie_DTO getMovie() {
        return movie;
    }

    public void setMovie(Movie_DTO movie) {
        this.movie = movie;
    }
}
