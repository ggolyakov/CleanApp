package com.woolf.cleanapp.ui.favorites;


import com.arellomobile.mvp.InjectViewState;
import com.woolf.cleanapp.base.BasePresenter;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.di.app.qualifier.Global;
import com.woolf.cleanapp.domain.interactor.FavoritesUseCase;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.Screens;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class FavoritesPresenter extends BasePresenter<IFavoritesView> {

    @Inject
    FavoritesUseCase favoritesUseCase;

    @Inject
    @Global
    Router router;


    public FavoritesPresenter() {
        ComponentManager.getInstance().getPhotosComponent().inject(this);
        setResultListener();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadFavorites();
    }

    @Override
    public void onBackPressed() {
        router.exit();
    }

    public void openDetailScreen(PhotoDomainModel model) {
        router.newScreenChain(Screens.DETAIL, model);
    }

    private void loadFavorites() {
        favoritesUseCase.execute(new DisposableSingleObserver<List<PhotoDomainModel>>() {
            @Override
            protected void onStart() {
                getViewState().hideEmptyListView();
                getViewState().showProgress();
            }

            @Override
            public void onSuccess(List<PhotoDomainModel> photoDomainModels) {
                getViewState().showList(photoDomainModels);
                if (photoDomainModels == null || photoDomainModels.isEmpty()) {
                    getViewState().showEmptyListView();
                }
                getViewState().hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showError(e.getMessage());
            }
        });


    }

    @Override
    public void onDestroy() {
        removeResultListener();
        favoritesUseCase.dispose();
        ComponentManager.getInstance().destroyPhotosComponent();
        super.onDestroy();
    }

     void reload() {
        loadFavorites();
    }



    private void setResultListener() {
        router.setResultListener(Screens.FAVORITES_RESULT, resultData -> {
            boolean isStatusChange = (boolean) resultData;
            if (isStatusChange) {
                loadFavorites();
            }
        });
    }

    private void removeResultListener() {
        router.removeResultListener(Screens.FAVORITES_RESULT);
    }
}
