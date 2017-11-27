package com.woolf.cleanapp.di.photo.module;

import com.woolf.cleanapp.di.photo.PhotoScope;
import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.domain.interactor.AddToFavoritesUseCase;
import com.woolf.cleanapp.domain.interactor.PhotoByIdUseCase;
import com.woolf.cleanapp.domain.interactor.RemoveFromFavoritesUseCase;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoUseCaseModule {

    @Provides
    @PhotoScope
    public PhotoByIdUseCase providePhotoByIdUseCase(IPhotoRepository photoRepository, IThreadExecutor threadExecutor) {
        return new PhotoByIdUseCase(photoRepository, threadExecutor);
    }

    @Provides
    @PhotoScope
    public AddToFavoritesUseCase provideAddToFavoritesUseCase(IPhotoRepository photoRepository, IThreadExecutor threadExecutor) {
        return new AddToFavoritesUseCase(photoRepository, threadExecutor);
    }

    @Provides
    @PhotoScope
    public RemoveFromFavoritesUseCase provideRemoveFromFavoritesUseCase(IPhotoRepository photoRepository, IThreadExecutor threadExecutor) {
        return new RemoveFromFavoritesUseCase(photoRepository,threadExecutor);
    }



}
