package org.project4.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "comment_episode")
public class Comment_Episode_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "content",  columnDefinition = "Nvarchar(max)")
    private String content;
    @Column(name = "time_add")
    private Date time_add;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User_Entity useradd;
    @ManyToOne
    @JoinColumn(name = "episode_id")
    private Episode_enitty episode;

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

    public Date getTime_add() {
        return time_add;
    }

    public void setTime_add(Date time_add) {
        this.time_add = time_add;
    }

    public User_Entity getUser_add() {
        return useradd;
    }

    public void setUser_add(User_Entity useradd) {
        this.useradd = useradd;
    }

    public Episode_enitty getEpisode() {
        return episode;
    }

    public void setEpisode(Episode_enitty episode) {
        this.episode = episode;
    }
}
