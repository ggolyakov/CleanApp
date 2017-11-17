
package com.woolf.cleanapp.data.model.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserEntity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("first_name")
    @Expose
    private String first_name;
    @SerializedName("last_name")
    @Expose
    private String last_name;
    @SerializedName("twitter_username")
    @Expose
    private String twitter_username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("portfolio_url")
    @Expose
    private String portfolio_url;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("total_likes")
    @Expose
    private Integer total_likes;
    @SerializedName("total_photos")
    @Expose
    private Integer total_photos;
    @SerializedName("total_collections")
    @Expose
    private Integer total_collections;
    @SerializedName("profile_image")
    @Expose
    private ProfileImageEntity profile_image;
    @SerializedName("links")
    @Expose
    private LinksEntity links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortfolio_url() {
        return portfolio_url;
    }

    public void setPortfolio_url(String portfolio_url) {
        this.portfolio_url = portfolio_url;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getTotal_likes() {
        return total_likes;
    }

    public void setTotal_likes(Integer total_likes) {
        this.total_likes = total_likes;
    }

    public Integer getTotal_photos() {
        return total_photos;
    }

    public void setTotal_photos(Integer total_photos) {
        this.total_photos = total_photos;
    }

    public Integer getTotal_collections() {
        return total_collections;
    }

    public void setTotal_collections(Integer total_collections) {
        this.total_collections = total_collections;
    }

    public ProfileImageEntity getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(ProfileImageEntity profile_image) {
        this.profile_image = profile_image;
    }

    public LinksEntity getLinks() {
        return links;
    }

    public void setLinks(LinksEntity links) {
        this.links = links;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTwitter_username() {
        return twitter_username;
    }

    public void setTwitter_username(String twitter_username) {
        this.twitter_username = twitter_username;
    }
}
