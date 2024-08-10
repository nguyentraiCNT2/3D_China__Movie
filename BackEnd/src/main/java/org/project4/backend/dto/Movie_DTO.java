package org.project4.backend.dto;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;

public class Movie_DTO {
    private Long id;
    private String vnname;
    private String cnname;
    private String description;
    private byte[] imageurl;
    private Date timeadd;
    private Date timeupdate;
    private User_DTO useradd;
    private User_DTO userupdate;
    private String author;
    private Long episodenumber;
    private String status;
    private Boolean newmovie;
    private Boolean hotmovie;
    private Boolean vipmovie;
    private BigDecimal price;
    private String imageBase64;
    private Long totalviews;
    private Long year;

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

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
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
