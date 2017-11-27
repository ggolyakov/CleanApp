package com.woolf.cleanapp.di.photos.module;

import com.woolf.cleanapp.di.photos.PhotosScope;
import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.domain.interactor.FavoritesUseCase;
import com.woolf.cleanapp.domain.interactor.PhotosUseCase;
import com.woolf.cleanapp.domain.repository.IPhotosRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotosUseCaseModule {

    @Provides
    @PhotosScope
    public PhotosUseCase providePhotoUseCase(IPhotosRepository photoRepository, IThreadExecutor threadExecutor) {
        return new PhotosUseCase(photoRepository, threadExecutor);
    }

    @Provides
    @PhotosScope
    public FavoritesUseCase provideFavoritesUseCase(IPhotosRepository photoRepository, IThreadExecutor threadExecutor) {
        return new FavoritesUseCase(photoRepository, threadExecutor);
    }

}
