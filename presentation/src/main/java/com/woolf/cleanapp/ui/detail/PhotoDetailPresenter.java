package com.woolf.cleanapp.ui.detail;


import com.arellomobile.mvp.InjectViewState;
import com.woolf.cleanapp.BuildConfig;
import com.woolf.cleanapp.base.BasePresenter;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.di.app.qualifier.Global;
import com.woolf.cleanapp.domain.interactor.AddToFavoritesUseCase;
import com.woolf.cleanapp.domain.interactor.PhotoByIdUseCase;
import com.woolf.cleanapp.domain.interactor.RemoveFromFavoritesUseCase;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.RequestParams;
import com.woolf.cleanapp.util.Screens;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class PhotoDetailPresenter extends BasePresenter<IPhotoDetailView> {


    @Inject
    PhotoByIdUseCase photoByIdUseCase;
    @Inject
    AddToFavoritesUseCase addToFavoritesUseCase;
    @Inject
    RemoveFromFavoritesUseCase removeFromFavoritesUseCase;

    @Inject
    @Global
    Router router;

    private String photoId;
    private Boolean isFavorite;
    private PhotoDomainModel photo;
    private HashMap<String, String> params;

    private boolean isStatusChange = false;

    public PhotoDetailPresenter(String photoId) {
        this.photoId = photoId;
        ComponentManager.getInstance().getPhotoComponent().inject(this);
        params = RequestParams.newBuilder()
                .append("client_id", BuildConfig.UnsplashApiId)
                .build();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgress();
        photoByIdUseCase.execute(new DisposableSingleObserver<PhotoDomainModel>() {
            @Override
            public void onSuccess(PhotoDomainModel photoDomainModel) {
                photo = photoDomainModel;
                isFavorite = photoDomainModel.getFavorite();
                updateFavorite();
                getViewState().fillImage(photoDomainModel);
                getViewState().fillUserInfo(photoDomainModel);
                getViewState().fillExifInfo(photoDomainModel.getExif());
                getViewState().fillLocationInfo(photoDomainModel.getLocation());
                getViewState().hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showError(e.getMessage());
            }
        }, new PhotoByIdUseCase.Params(photoId, params));
    }

    private void updateFavorite() {
        if (isFavorite) {
            getViewState().setIsFavorite();
        } else {
            getViewState().setIsNotFavorite();
        }
    }

    private void addToFavorites() {
        addToFavoritesUseCase.execute(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                isFavorite = true;
                photo.setFavorite(true);
                isStatusChange = !isStatusChange;
                updateFavorite();
            }

            @Override
            public void onError(Throwable e) {

            }
        }, photo);
    }

    private void removeFromFavorites() {
        removeFromFavoritesUseCase.execute(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                isFavorite = false;
                photo.setFavorite(false);
                isStatusChange = !isStatusChange;
                updateFavorite();
            }

            @Override
            public void onError(Throwable e) {

            }
        }, photoId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        photoByIdUseCase.dispose();
        addToFavoritesUseCase.dispose();
        removeFromFavoritesUseCase.dispose();
        ComponentManager.getInstance().destroyPhotoComponent();
    }

    public void onFavoriteClick() {
        if (isFavorite) {
            removeFromFavorites();
        } else {
            addToFavorites();
        }
    }

    @Override
    public void onBackPressed() {
        router.exitWithResult(Screens.FAVORITES_RESULT, isStatusChange);
    }
}
