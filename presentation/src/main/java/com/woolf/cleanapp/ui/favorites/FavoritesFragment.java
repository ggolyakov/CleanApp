package com.woolf.cleanapp.ui.favorites;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.woolf.cleanapp.R;
import com.woolf.cleanapp.base.BaseFragment;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.IBackButtonListener;
import com.woolf.cleanapp.util.ResUtils;
import com.woolf.cleanapp.util.adapter.FavoriteAdapter;
import com.woolf.cleanapp.util.view.EmptyView;
import com.woolf.cleanapp.util.view.ProgressView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends BaseFragment implements IFavoritesView, IBackButtonListener, ProgressView.RetryClickListener {

    @InjectPresenter
    FavoritesPresenter presenter;

    @BindView(R.id.pv_load_favorites)
    ProgressView pvLoad;
    @BindView(R.id.ev_favorites)
    EmptyView evEmptyList;
    @BindView(R.id.rv_favorites)
    RecyclerView rvFavorites;

    @Inject
    FavoriteAdapter favoriteAdapter;
    @Inject
    Provider<LinearLayoutManager> linearLayoutManager;
    @Inject
    Provider<GridLayoutManager> gridLayoutManager;
    @Inject
    ResUtils resUtils;


    private boolean isLandscape;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getPhotosComponent().inject(this);
        isLandscape = resUtils.getBoolean(R.bool.isLandscape);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        pvLoad.setRetryClickListener(this);
        rvFavorites.setAdapter(favoriteAdapter);
        rvFavorites.setLayoutManager(isLandscape ? gridLayoutManager.get() : linearLayoutManager.get());
        favoriteAdapter.setClickListener(photoDomainModel -> presenter.openDetailScreen(photoDomainModel));
    }

    @Override
    public void showProgress() {
        pvLoad.onStartLoading();
    }

    @Override
    public void hideProgress() {
        pvLoad.onCompleteLoading();
    }

    @Override
    public void showError(String error) {
        pvLoad.onErrorLoading(error);
    }

    @Override
    public void showList(List<PhotoDomainModel> photos) {
        favoriteAdapter.swap(photos);
        if (!presenter.isInRestoreState(this)) {
            rvFavorites.scheduleLayoutAnimation();
        } else {
            rvFavorites.setLayoutAnimation(null);
        }
    }

    @Override
    public void showEmptyListView() {
        evEmptyList.show();
    }

    @Override
    public void hideEmptyListView() {
        evEmptyList.hide();
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @Override
    public void onReload() {
        presenter.reload();
    }
}
