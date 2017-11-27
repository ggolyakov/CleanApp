package com.woolf.cleanapp.di.photo;


import com.woolf.cleanapp.di.photo.module.PhotoRepositoryModule;
import com.woolf.cleanapp.di.photo.module.PhotoUseCaseModule;
import com.woolf.cleanapp.domain.interactor.AddToFavoritesUseCase;
import com.woolf.cleanapp.domain.interactor.PhotoByIdUseCase;
import com.woolf.cleanapp.domain.interactor.RemoveFromFavoritesUseCase;
import com.woolf.cleanapp.ui.detail.PhotoDetailPresenter;

import dagger.Subcomponent;

@PhotoScope
@Subcomponent(modules = {
        PhotoRepositoryModule.class,
        PhotoUseCaseModule.class})
public interface PhotoComponent {

    void inject(PhotoDetailPresenter photoDetailPresenter);

    PhotoByIdUseCase photoByIdUseCase();

    AddToFavoritesUseCase addToFavorites();

    RemoveFromFavoritesUseCase removeFromFavorites();

    @Subcomponent.Builder
    interface Builder {
        PhotoComponent build();
    }

}
