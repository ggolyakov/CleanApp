package com.woolf.cleanapp.di.photo;

import com.woolf.cleanapp.di.app.ThreadingModule;
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
    public PhotosUseCase providePhotoUseCase(IPhotoRepository photoRepository, @Named(ThreadingModule.MAIN_SCHEDULER) Scheduler backgroundScheduler) {
        return new PhotosUseCase(photoRepository, backgroundScheduler);
    }
}
