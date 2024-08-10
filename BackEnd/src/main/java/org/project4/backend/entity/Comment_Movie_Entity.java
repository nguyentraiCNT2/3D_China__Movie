package org.project4.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "comment_movie")
public class Comment_Movie_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "content", columnDefinition = "Nvarchar(max)")
    private String content;
    @Column(name = "time_add")
    private Date timeadd;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User_Entity useradd;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie_Entity movie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeadd() {
        return timeadd;
    }

    public void setTimeadd(Date timeadd) {
        this.timeadd = timeadd;
    }

    public User_Entity getUseradd() {
        return useradd;
    }

    public void setUseradd(User_Entity useradd) {
        this.useradd = useradd;
    }

    public Movie_Entity getMovie() {
        return movie;
    }

    public void setMovie(Movie_Entity movie) {
        this.movie = movie;
    }
}
