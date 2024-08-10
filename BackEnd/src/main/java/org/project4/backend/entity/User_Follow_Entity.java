package org.project4.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "User_Follow")
public class User_Follow_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User_Entity user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie_Entity movie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
