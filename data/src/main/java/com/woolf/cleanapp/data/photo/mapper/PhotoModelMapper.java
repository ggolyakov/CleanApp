package com.woolf.cleanapp.data.photo.mapper;


import com.woolf.cleanapp.data.model.cache.ExifCacheModel;
import com.woolf.cleanapp.data.model.cache.LocationCacheModel;
import com.woolf.cleanapp.data.model.cache.PhotoCacheModel;
import com.woolf.cleanapp.data.model.cache.ProfileImageCacheModel;
import com.woolf.cleanapp.data.model.cache.UrlsCacheModel;
import com.woolf.cleanapp.data.model.cache.UserCacheModel;
import com.woolf.cleanapp.data.model.service.ExifEntity;
import com.woolf.cleanapp.data.model.service.LocationEntity;
import com.woolf.cleanapp.data.model.service.PhotoEntity;
import com.woolf.cleanapp.data.model.service.ProfileImageEntity;
import com.woolf.cleanapp.data.model.service.UrlsEntity;
import com.woolf.cleanapp.data.model.service.UserEntity;
import com.woolf.cleanapp.domain.model.ExifDomainModel;
import com.woolf.cleanapp.domain.model.LocationDomainModel;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.model.ProfileImageDomainModel;
import com.woolf.cleanapp.domain.model.UrlsDomainModel;
import com.woolf.cleanapp.domain.model.UserDomainModel;

import java.util.ArrayList;
import java.util.List;

public class PhotoModelMapper implements IPhotoModelMapper {

    @Override
    public PhotoDomainModel mapEntityToDomain(PhotoEntity photo) {
        PhotoDomainModel model = new PhotoDomainModel();
        UserDomainModel user = new UserDomainModel();
        UrlsDomainModel urls = new UrlsDomainModel();
        ExifDomainModel exif = new ExifDomainModel();
        LocationDomainModel location = new LocationDomainModel();
        ProfileImageDomainModel profile = new ProfileImageDomainModel();

        model.setId(photo.getId());
        model.setWidth(photo.getWidth());
        model.setHeight(photo.getHeight());
        model.setLikes(photo.getLikes());
        model.setDescription(photo.getDescription());
        model.setUser(user);
        model.setUrls(urls);
        model.setExif(exif);
        model.setLocation(location);
        //User
        if (photo.getUser() != null) {
            UserEntity userEntity = photo.getUser();
            user.setId(userEntity.getId());
            user.setFirstName(userEntity.getFirstName());
            user.setLastName(userEntity.getLastName());
            user.setProfileImage(profile);
            user.setUsername(userEntity.getUsername());
            //Profile
            if (userEntity.getProfileImage() != null) {
                ProfileImageEntity profileImageEntity = userEntity.getProfileImage();
                profile.setLarge(profileImageEntity.getLarge());
                profile.setMedium(profileImageEntity.getMedium());
                profile.setSmall(profileImageEntity.getSmall());
            }
        }
        //Urls
        if (photo.getUrls() != null) {
            UrlsEntity urlsEntity = photo.getUrls();
            urls.setFull(urlsEntity.getFull());
            urls.setRaw(urlsEntity.getRaw());
            urls.setRegular(urlsEntity.getRegular());
            urls.setSmall(urlsEntity.getSmall());
            urls.setThumb(urlsEntity.getThumb());
        }
        if (photo.getExif() != null) {
            ExifEntity exifEntity = photo.getExif();
            exif.setAperture(exifEntity.getAperture());
            exif.setExposureTime(exifEntity.getExposureTime());
            exif.setFocalLength(exifEntity.getFocalLength());
            exif.setIso(exifEntity.getIso());
            exif.setMake(exifEntity.getMake());
            exif.setModel(exifEntity.getModel());
        }
        //Location
        if (photo.getLocation() != null) {
            LocationEntity locationEntity = photo.getLocation();
            location.setCity(locationEntity.getCity());
            location.setCountry(locationEntity.getCountry());
            if (locationEntity.getPosition() != null) {
                location.setLatitude(locationEntity.getPosition().getLatitude());
                location.setLongitude(locationEntity.getPosition().getLongitude());
            }
        }
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

    @Override
    public PhotoDomainModel mapCacheToDomain(PhotoCacheModel photo) {
        PhotoDomainModel model = new PhotoDomainModel();
        UserDomainModel user = new UserDomainModel();
        UrlsDomainModel urls = new UrlsDomainModel();
        ExifDomainModel exif = new ExifDomainModel();
        LocationDomainModel location = new LocationDomainModel();
        ProfileImageDomainModel profile = new ProfileImageDomainModel();

        model.setId(photo.getId());
        model.setWidth(photo.getWidth());
        model.setHeight(photo.getHeight());
        model.setLikes(photo.getLikes());
        model.setDescription(photo.getDescription());
        model.setUser(user);
        model.setUrls(urls);
        model.setExif(exif);
        model.setLocation(location);
        //User
        if (photo.getUser() != null) {
            UserCacheModel userEntity = photo.getUser();
            user.setId(userEntity.getId());
            user.setFirstName(userEntity.getFirstName());
            user.setLastName(userEntity.getLastName());
            user.setProfileImage(profile);
            user.setUsername(userEntity.getUsername());
            //Profile
            if (userEntity.getProfileImage() != null) {
                ProfileImageCacheModel profileImageEntity = userEntity.getProfileImage();
                profile.setLarge(profileImageEntity.getLarge());
                profile.setMedium(profileImageEntity.getMedium());
                profile.setSmall(profileImageEntity.getSmall());
            }
        }
        //Urls
        if (photo.getUrls() != null) {
            UrlsCacheModel urlsEntity = photo.getUrls();
            urls.setFull(urlsEntity.getFull());
            urls.setRaw(urlsEntity.getRaw());
            urls.setRegular(urlsEntity.getRegular());
            urls.setSmall(urlsEntity.getSmall());
            urls.setThumb(urlsEntity.getThumb());
        }
        if (photo.getExif() != null) {
            ExifCacheModel exifEntity = photo.getExif();
            exif.setAperture(exifEntity.getAperture());
            exif.setExposureTime(exifEntity.getExposureTime());
            exif.setFocalLength(exifEntity.getFocalLength());
            exif.setIso(exifEntity.getIso());
            exif.setMake(exifEntity.getMake());
            exif.setModel(exifEntity.getModel());
        }
        //Location
        if (photo.getLocation() != null) {
            LocationCacheModel locationEntity = photo.getLocation();
            location.setCity(locationEntity.getCity());
            location.setCountry(locationEntity.getCountry());
            location.setLatitude(locationEntity.getLatitude());
            location.setLongitude(locationEntity.getLongitude());
        }
        return model;
    }

    @Override
    public List<PhotoDomainModel> mapCacheToDomainList(List<PhotoCacheModel> photos) {
        List<PhotoDomainModel> list = new ArrayList<>();
        if (photos != null && !photos.isEmpty()) {
            for (PhotoCacheModel photoEntity : photos) {
                list.add(mapCacheToDomain(photoEntity));
            }
        }
        return list;
    }

    @Override
    public PhotoCacheModel mapEntityToCache(PhotoEntity photo) {
        return null;
    }

    @Override
    public List<PhotoCacheModel> mapEntityToCacheList(List<PhotoEntity> photo) {
        return null;
    }
}
