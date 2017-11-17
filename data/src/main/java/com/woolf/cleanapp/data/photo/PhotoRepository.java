package com.woolf.cleanapp.data.photo;


import com.woolf.cleanapp.data.photo.provider.IPhotoDataProvider;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public class PhotoRepository implements IPhotoRepository {

    private IPhotoDataProvider networkProvider;
    //TODO implemented
    private IPhotoDataProvider cacheProvider;

    public PhotoRepository(IPhotoDataProvider networkProvider, IPhotoDataProvider cacheProvider) {
        this.networkProvider = networkProvider;
        this.cacheProvider = cacheProvider;
    }

    @Override
    public Flowable<List<PhotoDomainModel>> getPhotos(Map<String, String> params) {
        return networkProvider.getPhotos(params);
    }

}
