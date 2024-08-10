package org.project4.backend.dto;

import java.util.Date;
public class Schedule_DTO {
    private Long id;
    private String name;
    private String description;
    private Date timeadd;
    private Date timeupdate;
    private User_DTO useradd;
    private User_DTO userupdate;

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
}
