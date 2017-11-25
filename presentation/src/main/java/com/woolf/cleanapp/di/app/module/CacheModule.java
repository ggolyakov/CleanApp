package com.woolf.cleanapp.di.app.module;


import com.woolf.cleanapp.data.cache.Cache;
import com.woolf.cleanapp.data.cache.ICache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {

    @Provides
    @Singleton
    public ICache providePhotoCache() {
        return new Cache();
    }
}
