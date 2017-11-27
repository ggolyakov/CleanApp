package com.woolf.cleanapp.ui.photos;


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
import com.woolf.cleanapp.util.adapter.PhotoAdapter;
import com.woolf.cleanapp.util.view.ProgressView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosFragment extends BaseFragment implements IPhotosView, IBackButtonListener, ProgressView.RetryClickListener {

    @InjectPresenter
    PhotosPresenter photosPresenter;

    @BindView(R.id.rv_photos)
    RecyclerView rvPhotos;
    @BindView(R.id.pv_load_photos)
    ProgressView pvLoad;

    @Inject
    PhotoAdapter photoAdapter;
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
        return inflater.inflate(R.layout.fragment_photos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        pvLoad.setRetryClickListener(this);
        rvPhotos.setLayoutManager(isLandscape ? gridLayoutManager.get() : linearLayoutManager.get());
        rvPhotos.setAdapter(photoAdapter);
        photoAdapter.setClickListener(photoDomainModel -> photosPresenter.openDetailScreen(photoDomainModel));
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
    public boolean onBackPressed() {
        photosPresenter.onBackPressed();
        return true;
    }

    @Override
    public void onReload() {
        photosPresenter.reload();
    }
}
