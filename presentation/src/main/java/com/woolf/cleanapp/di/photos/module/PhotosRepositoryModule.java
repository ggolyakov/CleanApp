package com.woolf.cleanapp.di.photos.module;


import com.woolf.cleanapp.data.IApiService;
import com.woolf.cleanapp.data.cache.ICache;
import com.woolf.cleanapp.data.model.cache.PhotoCacheModel;
import com.woolf.cleanapp.data.model.service.PhotoEntity;
import com.woolf.cleanapp.data.photo.PhotosRepository;
import com.woolf.cleanapp.data.model.mapper.IModelMapper;
import com.woolf.cleanapp.data.photo.mapper.PhotoModelMapper;
import com.woolf.cleanapp.di.photos.PhotosScope;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotosRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotosRepositoryModule {

    @Provides
    @PhotosScope
    public IModelMapper<PhotoCacheModel, PhotoEntity, PhotoDomainModel> providePhotoModelMapper() {
        return new PhotoModelMapper();
    }

    @Provides
    @PhotosScope
    public IPhotosRepository providePhotoRepository(IApiService apiService, ICache cache, IModelMapper<PhotoCacheModel, PhotoEntity, PhotoDomainModel> modelMapper) {
        return new PhotosRepository(apiService, cache, modelMapper);
    }
}
