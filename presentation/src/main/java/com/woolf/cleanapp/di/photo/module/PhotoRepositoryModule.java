package com.woolf.cleanapp.di.photo.module;


import com.woolf.cleanapp.data.IApiService;
import com.woolf.cleanapp.data.cache.ICache;
import com.woolf.cleanapp.data.photo.PhotoRepository;
import com.woolf.cleanapp.data.photo.mapper.IPhotoModelMapper;
import com.woolf.cleanapp.di.photo.PhotoScope;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoRepositoryModule {

    @Provides
    @PhotoScope
    public IPhotoRepository providePhotoRepository(IApiService apiService, ICache cache, IPhotoModelMapper modelMapper) {
        return new PhotoRepository(apiService, cache, modelMapper);
    }
}
