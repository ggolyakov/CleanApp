package com.woolf.cleanapp.ui.detail;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.woolf.cleanapp.domain.model.ExifDomainModel;
import com.woolf.cleanapp.domain.model.LocationDomainModel;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;

public interface IPhotoDetailView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showError(String error);

    void fillImage(PhotoDomainModel domainModel);

    void fillUserInfo(PhotoDomainModel domainModel);

    void fillLocationInfo(LocationDomainModel locationDomainModel);

    void fillExifInfo(ExifDomainModel exif);

    void hideExifInfo();

    void setIsFavorite();

    void setIsNotFavorite();

}
