package org.project4.backend.entity;

import jakarta.persistence.*;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Movie")
public class Movie_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "vn_name"  , columnDefinition = "Nvarchar(max)")
    private String vnname;
    @Column(name = "cn_name"  , columnDefinition = "Nvarchar(max)")
    private String cnname;
    @Column(name = "description"  , columnDefinition = "Nvarchar(max)")
    private String description;
    @Column(name = "image_url"  ,columnDefinition = "varbinary(max)")
    private byte[] imageurl;
    @Column(name = "imageBase64"  , columnDefinition = "Nvarchar(max)")
    private String imageBase64;
    @Column(name = "time_add"  )
    private Date timeadd;
    @Column(name = "time_update"  )
    private Date timeupdate;
    @ManyToOne
    @JoinColumn(name = "user_add")
    private User_Entity useradd;
    @ManyToOne
    @JoinColumn(name = "user_update")
    private User_Entity userupdate;
    @Column(name = "author" , columnDefinition = "Nvarchar(max)" )
    private String author;
    @Column(name = "episode_number"  )
    private Long episodenumber;
    @Column(name = "status"  )
    private String status;
    @Column(name = "new_movie")
    private Boolean newmovie;
    @Column(name = "hot_movie")
    private Boolean hotmovie;
    @Column(name = "vip_movie")
    private Boolean vipmovie;
    @Column(name = "price"  )
    private BigDecimal price;
    @Column(name = "year")
    private Long year;
    @Column(name = "total_views")
    private Long totalviews;

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getTotalviews() {
        return totalviews;
    }

    public void setTotalviews(Long totalviews) {
        this.totalviews = totalviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVnname() {
        return vnname;
    }

    public void setVnname(String vnname) {
        this.vnname = vnname;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImageurl() {
        return imageurl;
    }

    public void setImageurl(byte[] imageurl) {
        this.imageurl = imageurl;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getEpisodenumber() {
        return episodenumber;
    }

    public void setEpisodenumber(Long episodenumber) {
        this.episodenumber = episodenumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getNewmovie() {
        return newmovie;
    }

    public void setNewmovie(Boolean newmovie) {
        this.newmovie = newmovie;
    }

    public Boolean getHotmovie() {
        return hotmovie;
    }

    public void setHotmovie(Boolean hotmovie) {
        this.hotmovie = hotmovie;
    }

    public Boolean getVipmovie() {
        return vipmovie;
    }

    public void setVipmovie(Boolean vipmovie) {
        this.vipmovie = vipmovie;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
