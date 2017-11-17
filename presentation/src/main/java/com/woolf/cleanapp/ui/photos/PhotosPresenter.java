package com.woolf.cleanapp.ui.photos;


import com.arellomobile.mvp.InjectViewState;
import com.woolf.cleanapp.BuildConfig;
import com.woolf.cleanapp.base.BasePresenter;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.domain.interactor.PhotosUseCase;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.RequestParams;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

@InjectViewState
public class PhotosPresenter extends BasePresenter<IPhotosView> {

    @Inject
    PhotosUseCase photosUseCase;

    private HashMap<String, String> params;


    public PhotosPresenter() {
        ComponentManager.getInstance().getPhotoComponent().inject(this);
        params = RequestParams.newBuilder()
                .append("client_id", BuildConfig.UnsplashApiId)
                .append("per_page","30")
                .build();

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgress();
        photosUseCase.execute(new DisposableSubscriber<List<PhotoDomainModel>>() {
            @Override
            public void onNext(List<PhotoDomainModel> photoDomainModels) {
                getViewState().showList(photoDomainModels);
            }

            @Override
            public void onError(Throwable t) {
                getViewState().showError(t.getMessage());
            }

            @Override
            public void onComplete() {
                getViewState().hideProgress();
            }
        }, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ComponentManager.getInstance().destroyPhotoComponent();
    }
}
