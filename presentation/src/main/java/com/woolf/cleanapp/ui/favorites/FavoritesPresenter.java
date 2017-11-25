package com.woolf.cleanapp.ui.favorites;


import com.arellomobile.mvp.InjectViewState;
import com.woolf.cleanapp.base.BasePresenter;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.domain.interactor.FavoritesUseCase;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

@InjectViewState
public class FavoritesPresenter extends BasePresenter<IFavoritesView> {

    @Inject
    FavoritesUseCase favoritesUseCase;

    public FavoritesPresenter() {
        ComponentManager.getInstance().getPhotoComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadFavorites();
    }

    private void loadFavorites() {
        getViewState().hideEmptyListView();
        getViewState().showProgress();
        favoritesUseCase.execute(new DisposableSubscriber<List<PhotoDomainModel>>() {
            @Override
            public void onNext(List<PhotoDomainModel> photoDomainModels) {
                if (photoDomainModels == null || photoDomainModels.isEmpty()) {
                    getViewState().showEmptyListView();
                } else {
                    getViewState().showList(photoDomainModels);
                }
            }

            @Override
            public void onError(Throwable t) {
                getViewState().showError(t.getMessage());
            }

            @Override
            public void onComplete() {
                getViewState().hideProgress();
            }
        }, new HashMap<>());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ComponentManager.getInstance().destroyPhotoComponent();
    }
}
