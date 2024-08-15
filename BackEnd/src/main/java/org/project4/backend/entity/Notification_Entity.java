package org.project4.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "notification")
public class Notification_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "content", columnDefinition = "Nvarchar(max)")
    private String content;
    @Column(name = "time_add"   )
    private Date timeadd;
    @Column(name = "time_update")
    private Date timeupdate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User_Entity user;
    @Column(name = "status")
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

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

    public Date getTimeupdate() {
        return timeupdate;
    }

    public void setTimeupdate(Date timeupdate) {
        this.timeupdate = timeupdate;
    }

    public User_Entity getUser() {
        return user;
    }

    public void setUser(User_Entity user) {
        this.user = user;
    }
}
