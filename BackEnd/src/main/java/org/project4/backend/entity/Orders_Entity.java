package org.project4.backend.entity;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "Orders")
public class Orders_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "point")
    private Long point;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User_Entity user;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie_Entity movie;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
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

    public User_Entity getUser() {
        return user;
    }

    public void setUser(User_Entity user) {
        this.user = user;
    }

    public Movie_Entity getMovie() {
        return movie;
    }

    public void setMovie(Movie_Entity movie) {
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
