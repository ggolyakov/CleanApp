package com.woolf.cleanapp.ui.main;


import com.arellomobile.mvp.InjectViewState;
import com.woolf.cleanapp.base.BasePresenter;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.util.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainPresenter extends BasePresenter<IMainView> {

    @Inject
    Router router;

    public MainPresenter() {
        ComponentManager.getInstance().getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        openMainScreen();
    }

    public void openMainScreen() {
        router.newRootScreen(Screens.MAIN);
    }

    public void openFavoritesScreen() {
        router.newRootScreen(Screens.FAVORITES);
    }

    public void openInfoScreen() {

    }
}
