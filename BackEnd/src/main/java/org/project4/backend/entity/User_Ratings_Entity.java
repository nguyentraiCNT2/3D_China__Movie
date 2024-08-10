package org.project4.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "User_Ratings")
public class User_Ratings_Entity {

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

    @Column(name = "rating")
    private Long rating;

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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
