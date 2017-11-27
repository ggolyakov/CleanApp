package com.woolf.cleanapp.di.photos;


import com.woolf.cleanapp.di.photo.PhotoComponent;
import com.woolf.cleanapp.di.photos.module.PhotosRepositoryModule;
import com.woolf.cleanapp.di.photos.module.PhotosUIModule;
import com.woolf.cleanapp.di.photos.module.PhotosUseCaseModule;
import com.woolf.cleanapp.ui.favorites.FavoritesFragment;
import com.woolf.cleanapp.ui.favorites.FavoritesPresenter;
import com.woolf.cleanapp.ui.photos.PhotosFragment;
import com.woolf.cleanapp.ui.photos.PhotosPresenter;

import dagger.Subcomponent;

@PhotosScope
@Subcomponent(modules = {
        PhotosRepositoryModule.class,
        PhotosUseCaseModule.class,
        PhotosUIModule.class})
public interface PhotosComponent {

    PhotoComponent.Builder photoComponentBuilder();

    void inject(PhotosPresenter photosPresenter);

    void inject(PhotosFragment photosFragment);

    void inject(FavoritesPresenter favoritesPresenter);

    void inject(FavoritesFragment favoritesFragment);




    @Subcomponent.Builder
    interface Builder {
        PhotosComponent build();
    }
}
