package org.project4.backend.entity;

import jakarta.persistence.*;

import java.io.File;
import java.sql.Date;
@Entity
@Table(name = "Users")
public class User_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username" , columnDefinition = "Nvarchar(max)")
    private String username;
    @Column(name = "password"  , columnDefinition = "Nvarchar(max)")
    private String password;
    @Column(name = "email"     , columnDefinition = "Nvarchar(max)")
    private String email;
    @Column(name = "time_add"     )
    private Date timeadd;
    @Column(name = "point"     )
    private Long point;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role_Entity role;
    @Column(name = "avatar" ,columnDefinition = "varbinary(max)")
    private byte[] avatar;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "fullname", columnDefinition = "Nvarchar(max)")
    private String fullname;

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

    public Role_Entity getRole() {
        return role;
    }

    public void setRole(Role_Entity role) {
        this.role = role;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
