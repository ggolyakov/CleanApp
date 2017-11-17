package com.woolf.cleanapp.data.photo;


import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import java.util.List;

import io.reactivex.Flowable;

public class PhotoCache implements IPhotoCache {

    @Override
    public Flowable<List<PhotoDomainModel>> getPhotos() {
        return null;
    }
}
