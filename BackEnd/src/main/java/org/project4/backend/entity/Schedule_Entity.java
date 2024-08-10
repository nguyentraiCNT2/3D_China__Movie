package org.project4.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "schedule")
public class Schedule_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name"    , columnDefinition = "Nvarchar(max)")
    private String name;
    @Column(name = "description"    , columnDefinition = "Nvarchar(max)")
    private String description;
    @Column(name = "time_add"    )
    private Date time_add;
    @Column(name = "time_update")
    private Date timeupdate;
    @ManyToOne
    @JoinColumn(name = "user_add_id")
    private User_Entity useradd;
        @ManyToOne
    @JoinColumn(name = "user_update_id")
    private User_Entity userupdate;

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

    public Date getTime_add() {
        return time_add;
    }

    public void setTime_add(Date time_add) {
        this.time_add = time_add;
    }

    public User_Entity getUseradd() {
        return useradd;
    }

    public void setUseradd(User_Entity useradd) {
        this.useradd = useradd;
    }

    public Date getTimeupdate() {
        return timeupdate;
    }

    public void setTimeupdate(Date timeupdate) {
        this.timeupdate = timeupdate;
    }

    public User_Entity getUserupdate() {
        return userupdate;
    }

    public void setUserupdate(User_Entity userupdate) {
        this.userupdate = userupdate;
    }
}
