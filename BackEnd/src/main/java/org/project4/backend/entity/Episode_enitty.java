package org.project4.backend.entity;

import jakarta.persistence.*;

import java.io.File;
import java.sql.Date;
@Entity
@Table(name = "Episodes_Movie")
public class Episode_enitty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name" , columnDefinition = "Nvarchar(max)")
    private String name;
    @Column(name = "description" , columnDefinition = "Nvarchar(max)")
    private String description;
    @Column(name = "time_add" )
    private Date timeadd;
    @Column(name = "time_update" )
    private Date timeupdate;
    @ManyToOne
    @JoinColumn(name = "user_add")
    private User_Entity useradd;
    @ManyToOne
    @JoinColumn(name = "user_update")
    private User_Entity userupdate;
    @Column(name = "views" )
    private Long views;
    @Column(name = "likes" )
    private Long likes;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie_Entity movie;
    @Column(name = "file_episodes",columnDefinition = "varbinary(max)")
    private byte[] fileepisodes;
    @Column(name = "subtitles",columnDefinition = "varbinary(max)")
    private byte[] subtitles;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeadd() {
        return timeadd;
    }

    public void setTimeadd(Date timeadd) {
        this.timeadd = timeadd;
    }

    public Date getTimeupdate() {
        return timeupdate;
    }

    public void setTimeupdate(Date timeupdate) {
        this.timeupdate = timeupdate;
    }

    public User_Entity getUseradd() {
        return useradd;
    }

    public void setUseradd(User_Entity useradd) {
        this.useradd = useradd;
    }

    public User_Entity getUserupdate() {
        return userupdate;
    }

    public void setUserupdate(User_Entity userupdate) {
        this.userupdate = userupdate;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Movie_Entity getMovie() {
        return movie;
    }

    public void setMovie(Movie_Entity movie) {
        this.movie = movie;
    }

    public byte[] getFileepisodes() {
        return fileepisodes;
    }

    public void setFileepisodes(byte[] fileepisodes) {
        this.fileepisodes = fileepisodes;
    }

    public byte[] getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(byte[] subtitles) {
        this.subtitles = subtitles;
    }
}
