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
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.adapter.PhotoAdapter;
import com.woolf.cleanapp.util.view.ProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosFragment extends BaseFragment implements IPhotosView {

    @InjectPresenter
    PhotosPresenter photosPresenter;

    @BindView(R.id.rv_photos)
    RecyclerView rvPhotos;
    @BindView(R.id.pv_load_photos)
    ProgressView pvLoad;

    private PhotoAdapter photoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        photoAdapter = new PhotoAdapter();

        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);
        if (isLandscape) {
            rvPhotos.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            rvPhotos.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

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
//        if (photosPresenter.isInRestoreState(this)) {
//            rvPhotos.scheduleLayoutAnimation();
//        }

    }
}
