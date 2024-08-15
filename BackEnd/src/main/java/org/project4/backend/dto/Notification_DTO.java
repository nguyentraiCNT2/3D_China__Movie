package org.project4.backend.dto;

import java.sql.Date;
public class Notification_DTO {
    private Long id;
    private String content;
    private Date timeadd;
    private Date timeupdate;
    private User_DTO user;
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

    public User_DTO getUser() {
        return user;
    }

    public void setUser(User_DTO user) {
        this.user = user;
    }
}
