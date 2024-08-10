package org.project4.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.User_Entity;

import java.sql.Date;

public class Orders_DTO {
    private Long id;
    private Long point;
    private User_DTO user;
    private Movie_DTO movie;
    private Date date;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
