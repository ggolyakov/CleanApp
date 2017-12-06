package com.woolf.cleanapp.ui.photos;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import java.util.List;

public interface IPhotosView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showError(String error);

    @StateStrategyType(SingleStateStrategy.class)
    void showList(List<PhotoDomainModel> photos);
}
