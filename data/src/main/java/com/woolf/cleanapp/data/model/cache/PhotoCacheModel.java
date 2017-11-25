package com.woolf.cleanapp.data.model.cache;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PhotoCacheModel extends RealmObject {

    @PrimaryKey
    private String id;
    private Integer width;
    private Integer height;
    private Integer likes;
    private String description;
    private UserCacheModel user;
    private UrlsCacheModel urls;
    private ExifCacheModel exif;
    private LocationCacheModel location;

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

    public UserCacheModel getUser() {
        return user;
    }

    public void setUser(UserCacheModel user) {
        this.user = user;
    }

    public UrlsCacheModel getUrls() {
        return urls;
    }

    public void setUrls(UrlsCacheModel urls) {
        this.urls = urls;
    }

    public ExifCacheModel getExif() {
        return exif;
    }

    public void setExif(ExifCacheModel exif) {
        this.exif = exif;
    }

    public LocationCacheModel getLocation() {
        return location;
    }

    public void setLocation(LocationCacheModel location) {
        this.location = location;
    }
}
