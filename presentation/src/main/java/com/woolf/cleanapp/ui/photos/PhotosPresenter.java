package com.woolf.cleanapp.ui.photos;


import com.arellomobile.mvp.InjectViewState;
import com.woolf.cleanapp.BuildConfig;
import com.woolf.cleanapp.base.BasePresenter;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.di.app.qualifier.Global;
import com.woolf.cleanapp.domain.interactor.PhotosUseCase;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.RequestParams;
import com.woolf.cleanapp.util.Screens;
import com.woolf.cleanapp.util.helper.ErrorHandler;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class PhotosPresenter extends BasePresenter<IPhotosView> {

    @Inject
    PhotosUseCase photosUseCase;
    @Inject
    ErrorHandler errorHandler;

    @Inject
    @Global
    Router router;

    private HashMap<String, String> params;

    public PhotosPresenter() {
        ComponentManager.getInstance().getPhotosComponent().inject(this);
        params = RequestParams.newBuilder()
                .append("client_id", BuildConfig.UnsplashApiId)
                .append("per_page", "30")
                .build();

    }

    public void openDetailScreen(PhotoDomainModel model) {
        router.navigateTo(Screens.DETAIL, model);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadPhotos();
    }

    private void loadPhotos() {
        getViewState().showProgress();
        photosUseCase.execute(new DisposableSingleObserver<List<PhotoDomainModel>>() {
            @Override
            public void onSuccess(List<PhotoDomainModel> photoDomainModels) {
                getViewState().showList(photoDomainModels);
                getViewState().hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showError(errorHandler.getError(e));
            }
        }, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ComponentManager.getInstance().destroyPhotosComponent();
    }

    @Override
    public void onBackPressed() {
        router.exit();
    }

    public void reload() {
        loadPhotos();
    }
}
