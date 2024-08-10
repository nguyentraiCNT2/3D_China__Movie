package org.project4.backend.dto;


import org.project4.backend.entity.Episode_enitty;

import java.sql.Date;
public class Comment_Episode_DTO {
    private Long id;
    private String content;
    private Date timeadd;
    private User_DTO useradd;
    private Episode_DTO episode;

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

    public User_DTO getUseradd() {
        return useradd;
    }

    public void setUseradd(User_DTO useradd) {
        this.useradd = useradd;
    }

    public Episode_DTO getEpisode() {
        return episode;
    }

    public void setEpisode(Episode_DTO episode) {
        this.episode = episode;
    }
}
