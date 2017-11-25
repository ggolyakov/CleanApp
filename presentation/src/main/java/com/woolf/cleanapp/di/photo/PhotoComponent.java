package com.woolf.cleanapp.di.photo;


import com.woolf.cleanapp.domain.interactor.FavoritesUseCase;
import com.woolf.cleanapp.domain.interactor.PhotoByIdUseCase;
import com.woolf.cleanapp.domain.interactor.PhotosUseCase;
import com.woolf.cleanapp.ui.detail.PhotoDetailPresenter;
import com.woolf.cleanapp.ui.favorites.FavoritesPresenter;
import com.woolf.cleanapp.ui.photos.PhotosFragment;
import com.woolf.cleanapp.ui.photos.PhotosPresenter;

import dagger.Subcomponent;

@PhotoScope
@Subcomponent(modules = {
        PhotoRepositoryModule.class,
        PhotoUseCaseModule.class})
public interface PhotoComponent {

    void inject(PhotosPresenter photosPresenter);

    void inject(PhotosFragment photosFragment);

    void inject(FavoritesPresenter favoritesPresenter);

    void inject(PhotoDetailPresenter photoDetailPresenter);

    PhotosUseCase photoUseCase();

    FavoritesUseCase favoritesUseCase();

    PhotoByIdUseCase photoByIdUseCase();

    @Subcomponent.Builder
    interface Builder {
        PhotoComponent.Builder scModule(PhotoRepositoryModule photoRepositoryModule);

        PhotoComponent build();
    }
}
