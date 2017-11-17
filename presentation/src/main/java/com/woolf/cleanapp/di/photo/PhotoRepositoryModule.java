package com.woolf.cleanapp.di.photo;


import com.woolf.cleanapp.data.IApiService;
import com.woolf.cleanapp.data.photo.IPhotoCache;
import com.woolf.cleanapp.data.photo.PhotoCache;
import com.woolf.cleanapp.data.photo.PhotoRepository;
import com.woolf.cleanapp.data.photo.mapper.IPhotoModelMapper;
import com.woolf.cleanapp.data.photo.mapper.PhotoModelMapper;
import com.woolf.cleanapp.data.photo.provider.IPhotoDataProvider;
import com.woolf.cleanapp.data.photo.provider.PhotoCacheDataProvider;
import com.woolf.cleanapp.data.photo.provider.PhotoNetworkDataProvider;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoRepositoryModule {

    @Provides
    @PhotoScope
    public IPhotoModelMapper providePhotoModelMapper() {
        return new PhotoModelMapper();
    }

    @Provides
    @PhotoScope
    public IPhotoRepository providePhotoRepository(@Named("Network") IPhotoDataProvider networkProvider, @Named("Cache") IPhotoDataProvider cacheProvider) {
        return new PhotoRepository(networkProvider, cacheProvider);
    }

    @Provides
    @PhotoScope
    @Named("Network")
    public IPhotoDataProvider provideNetworkDataProvider(IApiService apiService, IPhotoModelMapper photoModelMapper) {
        return new PhotoNetworkDataProvider(apiService, photoModelMapper);
    }

    @Provides
    @PhotoScope
    @Named("Cache")
    public IPhotoDataProvider provideCacheDataProvider(IPhotoCache iPhotoCache) {
        return new PhotoCacheDataProvider(iPhotoCache);
    }
    @Provides
    @PhotoScope
    public IPhotoCache providePhotoCache() {
        return new PhotoCache();
    }
}
