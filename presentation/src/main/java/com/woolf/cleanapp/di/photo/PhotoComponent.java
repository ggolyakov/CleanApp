package com.woolf.cleanapp.di.photo;


import com.woolf.cleanapp.domain.interactor.PhotosUseCase;
import com.woolf.cleanapp.ui.photos.PhotosPresenter;

import dagger.Subcomponent;

@PhotoScope
@Subcomponent(modules = {
        PhotoRepositoryModule.class,
        PhotoUseCaseModule.class})
public interface PhotoComponent {

    void inject(PhotosPresenter presenter);

    PhotosUseCase photoUseCase();

    @Subcomponent.Builder
    interface Builder {
        PhotoComponent.Builder scModule(PhotoRepositoryModule photoRepositoryModule);

        PhotoComponent build();

    }
}
