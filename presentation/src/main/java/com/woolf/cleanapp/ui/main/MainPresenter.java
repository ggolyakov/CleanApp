package com.woolf.cleanapp.ui.main;


import com.woolf.cleanapp.base.BasePresenter;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.di.app.qualifier.Global;
import com.woolf.cleanapp.util.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class MainPresenter extends BasePresenter<IMainView> {

    @Inject
    @Global
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
        router.newRootScreen(Screens.TAB);
    }

    @Override
    public void onBackPressed() {

    }
}
