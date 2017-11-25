package com.woolf.cleanapp.ui.detail;


import com.arellomobile.mvp.InjectViewState;
import com.woolf.cleanapp.BuildConfig;
import com.woolf.cleanapp.base.BasePresenter;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.domain.interactor.PhotoByIdUseCase;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.RequestParams;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

@InjectViewState
public class PhotoDetailPresenter extends BasePresenter<IPhotoDetailView> {

    @Inject
    PhotoByIdUseCase photoByIdUseCase;

    private String photoId;
    private HashMap<String, String> params;

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
        photoByIdUseCase.
                execute(new DisposableSubscriber<PhotoDomainModel>() {
                    @Override
                    public void onNext(PhotoDomainModel photoDomainModel) {
                        getViewState().fillImage(photoDomainModel);
                        getViewState().fillUserInfo(photoDomainModel);
                        getViewState().fillExifInfo(photoDomainModel.getExif());
                        getViewState().fillLocationInfo(photoDomainModel.getLocation());
                        getViewState().hideProgress();
                    }

                    @Override
                    public void onError(Throwable t) {
                        getViewState().showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }, new PhotoByIdUseCase.Params(photoId, params));
    }
}
