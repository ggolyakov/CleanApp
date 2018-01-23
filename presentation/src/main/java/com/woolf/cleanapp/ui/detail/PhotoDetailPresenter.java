package com.woolf.cleanapp.ui.detail;


import android.support.annotation.VisibleForTesting;

import com.arellomobile.mvp.InjectViewState;
import com.woolf.cleanapp.base.BasePresenter;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.di.app.qualifier.Global;
import com.woolf.cleanapp.domain.interactor.AddToFavoritesUseCase;
import com.woolf.cleanapp.domain.interactor.PhotoByIdUseCase;
import com.woolf.cleanapp.domain.interactor.RemoveFromFavoritesUseCase;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.Screens;
import com.woolf.cleanapp.util.helper.ErrorHandler;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
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
    ErrorHandler errorHandler;

    @Inject
    @Global
    Router router;

    private String photoId;
    private Boolean isFavorite;
    private PhotoDomainModel photo;

    private boolean isStatusChange = false;

    public PhotoDetailPresenter(String photoId) {
        this.photoId = photoId;
        ComponentManager.getInstance().getPhotoComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadPhoto();
    }

    private void loadPhoto() {
        photoByIdUseCase.execute(photoId,
                new DisposableSingleObserver<PhotoDomainModel>() {
                    @Override
                    protected void onStart() {
                        getViewState().showProgress();
                    }

                    @Override
                    public void onSuccess(PhotoDomainModel photoDomainModel) {
                        photo = photoDomainModel;
                        isFavorite = photoDomainModel.getFavorite();
                        fillView(photoDomainModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showError(errorHandler.getError(e));
                    }
                });
    }

    @Override
    public void onBackPressed() {
        router.exitWithResult(Screens.FAVORITES_RESULT, isStatusChange);
    }

    private void fillView(PhotoDomainModel photo) {
        updateFavorite();
        getViewState().fillImage(photo);
        getViewState().fillUserInfo(photo);
        getViewState().fillExifInfo(photo.getExif());
        getViewState().fillLocationInfo(photo.getLocation());
        getViewState().hideProgress();
    }

    private void updateFavorite() {
        if (isFavorite) {
            getViewState().setIsFavorite();
        } else {
            getViewState().setIsNotFavorite();
        }
    }

    private void addToFavorites() {
        addToFavoritesUseCase.execute(photo, new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                isFavorite = true;
                photo.setFavorite(true);
                isStatusChange = !isStatusChange;
                updateFavorite();
            }

            @Override
            public void onError(Throwable e) {
                updateFavorite();
            }
        });
    }

    private void removeFromFavorites() {
        removeFromFavoritesUseCase.execute(photoId, new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                isFavorite = false;
                photo.setFavorite(false);
                isStatusChange = !isStatusChange;
                updateFavorite();
            }

            @Override
            public void onError(Throwable e) {
                updateFavorite();
            }
        });
    }


    @Override
    public void onDestroy() {
        photoByIdUseCase.dispose();
        addToFavoritesUseCase.dispose();
        removeFromFavoritesUseCase.dispose();
        ComponentManager.getInstance().destroyPhotoComponent();
        super.onDestroy();
    }

    public void onFavoriteClick() {
        if (isFavorite) {
            removeFromFavorites();
        } else {
            addToFavorites();
        }
    }

    public void reload() {
        loadPhoto();
    }

    @VisibleForTesting
    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
