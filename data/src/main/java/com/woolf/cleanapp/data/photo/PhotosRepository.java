package com.woolf.cleanapp.data.photo;


import com.woolf.cleanapp.data.IApiService;
import com.woolf.cleanapp.data.cache.ICache;
import com.woolf.cleanapp.data.photo.mapper.IPhotoModelMapper;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotosRepository;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class PhotosRepository implements IPhotosRepository {


    private IApiService apiService;
    private ICache cache;
    private IPhotoModelMapper mapper;


    public PhotosRepository(IApiService apiService, ICache cache, IPhotoModelMapper mapper) {
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

    @Override
    public Single<PhotoDomainModel> getPhotoById(String id, Map<String, String> params) {
        return cache.isCached(id).flatMap(isCached -> {
            if (!isCached) {
                return apiService.getPhotoById(id, params).map(domainModel -> mapper.mapEntityToDomain(domainModel));
            } else {
                return cache.getPhotoById(id).map(model1 -> mapper.mapCacheToDomain(model1));
            }
        });
    }

}
