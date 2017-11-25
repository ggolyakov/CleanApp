package com.woolf.cleanapp.data.photo;


import com.woolf.cleanapp.data.IApiService;
import com.woolf.cleanapp.data.cache.ICache;
import com.woolf.cleanapp.data.photo.mapper.IPhotoModelMapper;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public class PhotoRepository implements IPhotoRepository {


    private IApiService apiService;
    private ICache cache;
    private IPhotoModelMapper mapper;


    public PhotoRepository(IApiService apiService, ICache cache, IPhotoModelMapper mapper) {
        this.apiService = apiService;
        this.cache = cache;
        this.mapper = mapper;
    }

    @Override
    public Flowable<List<PhotoDomainModel>> getPhotos(Map<String, String> params) {
        return apiService.getPhotos(params).map(photoEntities -> mapper.mapEntityToDomainList(photoEntities));
    }

    @Override
    public Flowable<List<PhotoDomainModel>> getFavorites() {
        return cache.getFavorites().map(photoCacheModels -> mapper.mapCacheToDomainList(photoCacheModels));
    }

    @Override
    public Flowable<PhotoDomainModel> getPhotoById(String id, Map<String, String> params) {
        return cache.isCached(id).flatMap(isCached -> {
            if (!isCached) {
                return apiService.getPhotoById(id, params).map(domainModel -> mapper.mapEntityToDomain(domainModel));
            } else {
                return cache.getPhotoById(id).map(model1 -> mapper.mapCacheToDomain(model1));
            }
        });
    }

}
