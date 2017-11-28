package com.woolf.cleanapp.ui.tab;


import com.arellomobile.mvp.InjectViewState;
import com.woolf.cleanapp.base.BasePresenter;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.di.app.qualifier.Global;
import com.woolf.cleanapp.di.app.qualifier.Local;
import com.woolf.cleanapp.util.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class TabPresenter extends BasePresenter<ITabView> {

    @Inject
    @Local
    Router router;

    @Inject
    @Global
    Router globalRouter;

    public TabPresenter() {
        ComponentManager.getInstance().getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        openMainScreen();
    }

    public void openMainScreen() {
        router.replaceScreen(Screens.MAIN);
    }

    public void openFavoritesScreen() {
        router.replaceScreen(Screens.FAVORITES);
    }

    public void openInfoScreen() {
        router.replaceScreen(Screens.INFO);
    }

    @Override
    public void onBackPressed() {
        globalRouter.exit();
    }
}
