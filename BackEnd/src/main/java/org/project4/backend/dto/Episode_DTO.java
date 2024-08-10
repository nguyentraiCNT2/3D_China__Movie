package org.project4.backend.dto;

import java.io.File;
import java.sql.Date;
public class Episode_DTO {
    private Long id;
    private String name;
    private String description;
    private Date timeadd;
    private Date timeupdate;
    private User_DTO useradd;
    private User_DTO userupdate;
    private Long views;
    private Long likes;
    private Movie_DTO movie;
    private byte[]  fileepisodes;
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

    public User_DTO getUseradd() {
        return useradd;
    }

    public void setUseradd(User_DTO useradd) {
        this.useradd = useradd;
    }

    public User_DTO getUserupdate() {
        return userupdate;
    }

    public void setUserupdate(User_DTO userupdate) {
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

    public Movie_DTO getMovie() {
        return movie;
    }

    public void setMovie(Movie_DTO movie) {
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
