package com.woolf.cleanapp.data.photo;


import com.woolf.cleanapp.data.IApiService;
import com.woolf.cleanapp.data.cache.ICache;
import com.woolf.cleanapp.data.model.cache.PhotoCacheModel;
import com.woolf.cleanapp.data.model.service.PhotoEntity;
import com.woolf.cleanapp.data.model.mapper.IModelMapper;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import io.reactivex.Completable;
import io.reactivex.Single;

public class PhotoRepository implements IPhotoRepository {

    private IApiService apiService;
    private ICache cache;
    private IModelMapper<PhotoCacheModel, PhotoEntity, PhotoDomainModel> mapper;

    public PhotoRepository(IApiService apiService, ICache cache, IModelMapper<PhotoCacheModel, PhotoEntity, PhotoDomainModel> mapper) {
        this.apiService = apiService;
        this.cache = cache;
        this.mapper = mapper;
    }

    @Override
    public Completable addToFavorite(PhotoDomainModel photo) {
        return Single.fromCallable(() -> mapper.mapDomainToCache(photo))
                .flatMapCompletable(model -> cache.addToFavorites(model));
    }

    @Override
    public Completable removeFromFavorite(String id) {
        return cache.removeFromFavorites(id);
    }

    @Override
    public Single<PhotoDomainModel> getPhotoById(String id) {
        return cache.isCached(id).flatMap(isCached -> {
            if (!isCached) {
                return apiService.getPhotoById(id).map(domainModel -> mapper.mapEntityToDomain(domainModel));
            } else {
                return cache.getPhotoById(id).map(model1 -> mapper.mapCacheToDomain(model1));
            }
        });
    }
}
