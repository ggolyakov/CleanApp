package com.woolf.cleanapp.di.photo;

import com.woolf.cleanapp.di.app.module.ThreadingModule;
import com.woolf.cleanapp.domain.interactor.FavoritesUseCase;
import com.woolf.cleanapp.domain.interactor.PhotoByIdUseCase;
import com.woolf.cleanapp.domain.interactor.PhotosUseCase;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class PhotoUseCaseModule {

    @Provides
    @PhotoScope
    public PhotosUseCase providePhotoUseCase(IPhotoRepository photoRepository,
                                             @Named(ThreadingModule.MAIN_SCHEDULER) Scheduler mainScheduler,
                                             @Named(ThreadingModule.BACKGROUND_SCHEDULER) Scheduler backgroundScheduler) {
        return new PhotosUseCase(photoRepository, mainScheduler, backgroundScheduler);
    }

    @Provides
    @PhotoScope
    public FavoritesUseCase provideFAvoritesUseCase(IPhotoRepository photoRepository,
                                                    @Named(ThreadingModule.MAIN_SCHEDULER) Scheduler mainScheduler,
                                                    @Named(ThreadingModule.BACKGROUND_SCHEDULER) Scheduler backgroundScheduler) {
        return new FavoritesUseCase(photoRepository, mainScheduler, backgroundScheduler);
    }

    @Provides
    @PhotoScope
    public PhotoByIdUseCase providePhotoByIdUseCase(IPhotoRepository photoRepository,
                                                    @Named(ThreadingModule.MAIN_SCHEDULER) Scheduler mainScheduler,
                                                    @Named(ThreadingModule.BACKGROUND_SCHEDULER) Scheduler backgroundScheduler) {
        return new PhotoByIdUseCase(photoRepository, mainScheduler, backgroundScheduler);
    }

}
