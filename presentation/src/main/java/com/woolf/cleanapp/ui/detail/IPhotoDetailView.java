package com.woolf.cleanapp.ui.detail;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.woolf.cleanapp.domain.model.ExifDomainModel;
import com.woolf.cleanapp.domain.model.LocationDomainModel;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface IPhotoDetailView extends MvpView {

    void showProgress();

    void hideProgress();

    void showError(String error);

    void fillImage(PhotoDomainModel photo);

    void fillUserInfo(PhotoDomainModel photo);

    void fillLocationInfo(LocationDomainModel location);

    void fillExifInfo(ExifDomainModel exif);

    void setIsFavorite();

    void setIsNotFavorite();

}
