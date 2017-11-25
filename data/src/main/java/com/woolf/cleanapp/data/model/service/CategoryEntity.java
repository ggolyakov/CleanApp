
package com.woolf.cleanapp.data.model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryEntity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("photo_count")
    @Expose
    private Integer photo_count;
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

    public Integer getPhotoCount() {
        return photo_count;
    }

    public void setPhotoCount(Integer photo_count) {
        this.photo_count = photo_count;
    }

    public LinksEntity getLinks() {
        return links;
    }

    public void setLinks(LinksEntity links) {
        this.links = links;
    }


}
