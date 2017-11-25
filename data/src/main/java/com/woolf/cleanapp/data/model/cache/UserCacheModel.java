package com.woolf.cleanapp.data.model.cache;


import io.realm.RealmObject;

public class UserCacheModel extends RealmObject {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private ProfileImageCacheModel profileImage;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ProfileImageCacheModel getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImageCacheModel profileImage) {
        this.profileImage = profileImage;
    }
}
