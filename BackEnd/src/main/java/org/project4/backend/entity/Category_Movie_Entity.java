package org.project4.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category_movie")
public class Category_Movie_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category_Entity category;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie_Entity movie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category_Entity getCategory() {
        return category;
    }

    public void setCategory(Category_Entity category) {
        this.category = category;
    }

    public Movie_Entity getMovie() {
        return movie;
    }

    public void setMovie(Movie_Entity movie) {
        this.movie = movie;
    }
}
