
package com.woolf.cleanapp.data.model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoEntity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("exif")
    @Expose
    private ExifEntity exif;
    @SerializedName("location")
    @Expose
    private LocationEntity location;
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
    @SerializedName("current_user_collections")
    @Expose
    private List<CurrentUserCollectionEntity> current_user_collections = null;
    @SerializedName("urls")
    @Expose
    private UrlsEntity urls;
    @SerializedName("links")
    @Expose
    private LinksEntity links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(String updated_at) {
        this.updated_at = updated_at;
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

    public List<CurrentUserCollectionEntity> getCurrentUserCollections() {
        return current_user_collections;
    }

    public void setCurrentUserCollections(List<CurrentUserCollectionEntity> current_user_collections) {
        this.current_user_collections = current_user_collections;
    }

    public UrlsEntity getUrls() {
        return urls;
    }

    public void setUrls(UrlsEntity urls) {
        this.urls = urls;
    }

    public LinksEntity getLinks() {
        return links;
    }

    public void setLinks(LinksEntity links) {
        this.links = links;
    }

    public ExifEntity getExif() {
        return exif;
    }

    public void setExif(ExifEntity exif) {
        this.exif = exif;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }
}
