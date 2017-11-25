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
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.adapter.PhotoAdapter;
import com.woolf.cleanapp.util.view.EmptyView;
import com.woolf.cleanapp.util.view.ProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends BaseFragment implements IFavoritesView {

    @InjectPresenter
    FavoritesPresenter presenter;

    @BindView(R.id.pv_load_favorites)
    ProgressView pvLoad;
    @BindView(R.id.ev_favorites)
    EmptyView evEmpltyList;
    @BindView(R.id.rv_favorites)
    RecyclerView rvFavorites;

    private PhotoAdapter photoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        photoAdapter = new PhotoAdapter();

        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);
        if (isLandscape) {
            rvFavorites.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            rvFavorites.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        rvFavorites.setAdapter(photoAdapter);
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
        photoAdapter.swap(photos);
    }

    @Override
    public void showEmptyListView() {
        evEmpltyList.show();
    }

    @Override
    public void hideEmptyListView() {
        evEmpltyList.hide();
    }
}
