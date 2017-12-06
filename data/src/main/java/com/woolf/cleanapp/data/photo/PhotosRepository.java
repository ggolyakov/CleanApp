package com.woolf.cleanapp.data.photo;


import com.woolf.cleanapp.data.IApiService;
import com.woolf.cleanapp.data.cache.ICache;
import com.woolf.cleanapp.data.model.cache.PhotoCacheModel;
import com.woolf.cleanapp.data.model.service.PhotoEntity;
import com.woolf.cleanapp.data.model.mapper.IModelMapper;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotosRepository;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class PhotosRepository implements IPhotosRepository {


    private IApiService apiService;
    private ICache cache;
    private IModelMapper<PhotoCacheModel, PhotoEntity, PhotoDomainModel> mapper;


    public PhotosRepository(IApiService apiService, ICache cache, IModelMapper<PhotoCacheModel, PhotoEntity, PhotoDomainModel> mapper) {
        this.apiService = apiService;
        this.cache = cache;
        this.mapper = mapper;
    }

    @Override
    public Single<List<PhotoDomainModel>> getPhotos(Map<String, String> params) {
        return apiService.getPhotos(params).map(photoEntities -> mapper.mapEntityToDomainList(photoEntities));
    }

    @Override
    public Single<List<PhotoDomainModel>> getFavorites() {
        return cache.getFavorites().map(photoCacheModels -> mapper.mapCacheToDomainList(photoCacheModels));
    }
}
