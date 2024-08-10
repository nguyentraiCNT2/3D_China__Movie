package org.project4.backend.dto;
public class Category_Movie_DTO {
    private Long id;

    private Category_DTO category;

    private Movie_DTO movie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category_DTO getCategory() {
        return category;
    }

    public void setCategory(Category_DTO category) {
        this.category = category;
    }

    public Movie_DTO getMovie() {
        return movie;
    }

    public void setMovie(Movie_DTO movie) {
        this.movie = movie;
    }
}
