package com.woolf.cleanapp.data.photo.mapper;


import com.woolf.cleanapp.data.model.service.PhotoEntity;
import com.woolf.cleanapp.data.model.service.ProfileImageEntity;
import com.woolf.cleanapp.data.model.service.UrlsEntity;
import com.woolf.cleanapp.data.model.service.UserEntity;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.model.ProfileImageDomainModel;
import com.woolf.cleanapp.domain.model.UrlsDomainModel;
import com.woolf.cleanapp.domain.model.UserDomainModel;

import java.util.ArrayList;
import java.util.List;

public class PhotoModelMapper implements IPhotoModelMapper {

    @Override
    public PhotoDomainModel mapEntityToDomain(PhotoEntity photo) {
        UserEntity userEntity = photo.getUser();
        UrlsEntity urlsEntity = photo.getUrls();
        ProfileImageEntity profileImageEntity = userEntity.getProfile_image();
        PhotoDomainModel model = new PhotoDomainModel();
        UserDomainModel user = new UserDomainModel();
        UrlsDomainModel urls = new UrlsDomainModel();
        ProfileImageDomainModel profile = new ProfileImageDomainModel();
        model.setId(photo.getId());
        model.setWidth(photo.getWidth());
        model.setHeight(photo.getHeight());
        model.setLikes(photo.getLikes());
        model.setDescription(photo.getDescription());
        model.setUser(user);
        model.setUrls(urls);
        //User
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirst_name());
        user.setLastName(userEntity.getLast_name());
        user.setProfileImage(profile);
        user.setUsername(userEntity.getUsername());
        //Urls
        urls.setFull(urlsEntity.getFull());
        urls.setRaw(urlsEntity.getRaw());
        urls.setRegular(urlsEntity.getRegular());
        urls.setSmall(urlsEntity.getSmall());
        urls.setThumb(urlsEntity.getThumb());
        //Profile
        profile.setLarge(profileImageEntity.getLarge());
        profile.setMedium(profileImageEntity.getMedium());
        profile.setSmall(profileImageEntity.getSmall());
        return model;
    }

    @Override
    public List<PhotoDomainModel> mapEntityToDomainList(List<PhotoEntity> photos) {
        List<PhotoDomainModel> list = new ArrayList<>();
        if (photos != null && !photos.isEmpty()) {
            for (PhotoEntity photoEntity : photos) {
                list.add(mapEntityToDomain(photoEntity));
            }
        }
        return list;
    }
}
