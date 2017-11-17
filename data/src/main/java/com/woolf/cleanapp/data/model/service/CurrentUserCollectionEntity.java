
package com.woolf.cleanapp.data.model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentUserCollectionEntity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("published_at")
    @Expose
    private String published_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("curated")
    @Expose
    private Boolean curated;
    @SerializedName("cover_photo")
    @Expose
    private CoverPhotoEntity cover_photo;
    @SerializedName("user")
    @Expose
    private UserEntity user;
    @SerializedName("links")
    @Expose
    private LinksEntity links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Boolean getCurated() {
        return curated;
    }

    public void setCurated(Boolean curated) {
        this.curated = curated;
    }

    public CoverPhotoEntity getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(CoverPhotoEntity cover_photo) {
        this.cover_photo = cover_photo;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LinksEntity getLinks() {
        return links;
    }

    public void setLinks(LinksEntity links) {
        this.links = links;
    }

}
