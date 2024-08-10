package org.project4.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Schedule_Movie")
public class Schedule_Movie_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie_Entity movie;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule_Entity schedule;

    public Movie_Entity getMovie() {
        return movie;
    }

    public void setMovie(Movie_Entity movie) {
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Schedule_Entity getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule_Entity schedule) {
        this.schedule = schedule;
    }
}
