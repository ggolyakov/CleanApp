
package com.woolf.cleanapp.data.model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoverPhotoEntity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("liked_by_user")
    @Expose
    private Boolean liked_by_user;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("user")
    @Expose
    private UserEntity user;
    @SerializedName("urls")
    @Expose
    private UrlsEntity urls;
    @SerializedName("categories")
    @Expose
    private List<CategoryEntity> categories = null;
    @SerializedName("links")
    @Expose
    private LinksEntity links;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Boolean getLikedByUser() {
        return liked_by_user;
    }

    public void setLikedByUser(Boolean liked_by_user) {
        this.liked_by_user = liked_by_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UrlsEntity getUrls() {
        return urls;
    }

    public void setUrls(UrlsEntity urls) {
        this.urls = urls;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public LinksEntity getLinks() {
        return links;
    }

    public void setLinks(LinksEntity links) {
        this.links = links;
    }


}
