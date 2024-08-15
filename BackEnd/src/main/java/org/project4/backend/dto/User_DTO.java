package org.project4.backend.dto;

import jakarta.persistence.Column;

import java.io.File;
import java.sql.Date;
public class User_DTO {
    private Long id;
    private String username;
    private String fullname;
    private String password;
    private String email;
    private Date timeadd;
    private Long point;
    private Role_DTO role;
    private byte[]  avatar;
    private Boolean status;
    private Boolean active;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTime_add() {
        return timeadd;
    }

    public void setTime_add(Date time_add) {
        this.timeadd = time_add;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public Role_DTO getRole() {
        return role;
    }

    public void setRole(Role_DTO role) {
        this.role = role;
    }

    public byte[]  getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[]  avatar) {
        this.avatar = avatar;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
