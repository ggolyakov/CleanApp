package com.woolf.cleanapp.data.photo.provider;


import com.woolf.cleanapp.data.photo.IPhotoCache;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public class PhotoCacheDataProvider implements IPhotoDataProvider {

    IPhotoCache photoCache;

    public PhotoCacheDataProvider(IPhotoCache photoCache) {
        this.photoCache = photoCache;
    }

    @Override
    public Flowable<List<PhotoDomainModel>> getPhotos(Map<String, String> params) {
        return photoCache.getPhotos();
    }
}
