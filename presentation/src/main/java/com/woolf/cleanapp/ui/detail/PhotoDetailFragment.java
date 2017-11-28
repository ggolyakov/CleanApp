package com.woolf.cleanapp.ui.detail;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.woolf.cleanapp.R;
import com.woolf.cleanapp.base.BaseFragment;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.domain.model.ExifDomainModel;
import com.woolf.cleanapp.domain.model.LocationDomainModel;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.util.IBackButtonListener;
import com.woolf.cleanapp.util.ResUtils;
import com.woolf.cleanapp.util.view.ProgressView;

import java.text.MessageFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoDetailFragment extends BaseFragment implements IPhotoDetailView, IBackButtonListener {

    public static final String ID = "PhotoDetailFragment.ID";
    public static final String HEIGHT = "PhotoDetailFragment.HEIGHT";
    public static final String WIDTH = "PhotoDetailFragment.WIDTH";

    @InjectPresenter
    PhotoDetailPresenter detailPresenter;

    @Inject
    ResUtils resUtils;

    @BindView(R.id.pv_detail_photo)
    ProgressView pvLoad;
    @BindView(R.id.iv_detail_favorites)
    ImageView ivFavorite;

    @BindView(R.id.sdv_detail_photo)
    SimpleDraweeView sdvPhoto;
    @BindView(R.id.tv_detail_username)
    TextView tvUserName;
    @BindView(R.id.sdv_detail_avatar)
    SimpleDraweeView sdvAvatar;
    @BindView(R.id.tv_detail_size_value)
    TextView tvDimensions;

    @BindView(R.id.tv_detail_camera_make_value)
    TextView tvCameraMake;
    @BindView(R.id.tv_detail_camera_model_value)
    TextView tvCameraModel;
    @BindView(R.id.tv_detail_camera_focal_length_value)
    TextView tvFocalLength;
    @BindView(R.id.tv_detail_camera_aperture_value)
    TextView tvAperture;
    @BindView(R.id.tv_detail_camera_shutter_speed_value)
    TextView tvShutterSpeed;
    @BindView(R.id.tv_detail_camera_iso_value)
    TextView tvISO;
    @BindView(R.id.tv_detail_location_country_value)
    TextView tvCountry;
    @BindView(R.id.tv_detail_location_city_value)
    TextView tvCity;

    @ProvidePresenter
    PhotoDetailPresenter provideDetailPresenter() {
        return new PhotoDetailPresenter(getArguments().getString(ID));
    }

    public static PhotoDetailFragment newInstance(String id, Integer height, Integer width) {
        PhotoDetailFragment photoDetailFragment = new PhotoDetailFragment();
        Bundle args = new Bundle();
        args.putString(ID, id);
        args.putInt(HEIGHT, height);
        args.putInt(WIDTH, width);
        photoDetailFragment.setArguments(args);
        return photoDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getPhotoComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        configImageView();
    }

    @OnClick(R.id.iv_detail_back)
    void onBackClick() {
        detailPresenter.onBackPressed();
    }

    @OnClick(R.id.iv_detail_favorites)
    void onFavoriteClick() {
        detailPresenter.onFavoriteClick();
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
    public void fillImage(PhotoDomainModel domainModel) {
        Uri image = Uri.parse(domainModel.getUrls().getRegular());
        Uri preview = Uri.parse(domainModel.getUrls().getSmall());

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setLowResImageRequest(ImageRequest.fromUri(preview))
                .setImageRequest(ImageRequest.fromUri(image))
                .setOldController(sdvPhoto.getController())
                .build();
        sdvPhoto.setAspectRatio(domainModel.getWidth().floatValue() / domainModel.getHeight().floatValue());
        sdvPhoto.setController(controller);

        tvDimensions.setText(MessageFormat.format(resUtils.getString(R.string.label_image_size), domainModel.getHeight(), domainModel.getWidth()));
    }

    @Override
    public void fillUserInfo(PhotoDomainModel domainModel) {
        Uri avatar = Uri.parse(domainModel.getUser().getProfileImage().getMedium());
        sdvAvatar.setImageURI(avatar);
        tvUserName.setText(domainModel.getUser().getFirstName() + " " + domainModel.getUser().getLastName());
    }

    @Override
    public void fillLocationInfo(LocationDomainModel locationDomainModel) {
        tvCountry.setText(locationDomainModel.getCountry());
        tvCity.setText(locationDomainModel.getCity());
    }

    @Override
    public void fillExifInfo(ExifDomainModel exif) {
        tvCameraMake.setText(exif.getMake());
        tvCameraModel.setText(exif.getModel());
        tvFocalLength.setText(exif.getFocalLength());
        tvAperture.setText(exif.getAperture());
        tvShutterSpeed.setText(exif.getExposureTime());
        tvISO.setText(String.valueOf(exif.getIso()));
    }

    @Override
    public void hideExifInfo() {

    }

    @Override
    public void setIsFavorite() {
        ivFavorite.setImageResource(R.drawable.ic_favorite_fill);
    }

    @Override
    public void setIsNotFavorite() {
        ivFavorite.setImageResource(R.drawable.ic_favorite);
    }

    private void configImageView() {
        Integer height = getArguments().getInt(HEIGHT);
        Integer width = getArguments().getInt(WIDTH);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) sdvPhoto.getLayoutParams();
        params.dimensionRatio = "w," + height + ":" + width;
    }


    @Override
    public boolean onBackPressed() {
        detailPresenter.onBackPressed();
        return true;
    }
}
