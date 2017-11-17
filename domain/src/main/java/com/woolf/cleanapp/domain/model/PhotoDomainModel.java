package com.woolf.cleanapp.domain.model;


import java.io.Serializable;

public class PhotoDomainModel implements Serializable {

    private String id;
    private Integer width;
    private Integer height;
    private Integer likes;
    private String description;
    private UserDomainModel user;
    private UrlsDomainModel urls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDomainModel getUser() {
        return user;
    }

    public void setUser(UserDomainModel user) {
        this.user = user;
    }

    public UrlsDomainModel getUrls() {
        return urls;
    }

    public void setUrls(UrlsDomainModel urls) {
        this.urls = urls;
    }
}
