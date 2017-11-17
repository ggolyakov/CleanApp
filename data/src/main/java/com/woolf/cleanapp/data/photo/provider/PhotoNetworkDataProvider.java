package com.woolf.cleanapp.data.photo.provider;


import com.woolf.cleanapp.data.IApiService;
import com.woolf.cleanapp.data.photo.mapper.IPhotoModelMapper;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public class PhotoNetworkDataProvider implements IPhotoDataProvider {

    private IApiService apiService;
    private IPhotoModelMapper converter;

    public PhotoNetworkDataProvider(IApiService apiService, IPhotoModelMapper converter) {
        this.apiService = apiService;
        this.converter = converter;
    }


    @Override
    public Flowable<List<PhotoDomainModel>> getPhotos(Map<String, String> params) {
        return apiService.getPhotos(params).map(photos -> converter.mapEntityToDomainList(photos));
    }
}
