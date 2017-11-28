package com.woolf.cleanapp.ui.tab;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.woolf.cleanapp.R;
import com.woolf.cleanapp.base.BaseFragment;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.di.app.qualifier.Local;
import com.woolf.cleanapp.util.FragmentFactory;
import com.woolf.cleanapp.util.IBackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class TabFragment extends BaseFragment implements ITabView,
        BottomNavigationView.OnNavigationItemSelectedListener, IBackButtonListener {

    @BindView(R.id.bnv_menu)
    BottomNavigationView bnvMenu;

    @InjectPresenter
    TabPresenter mainPresenter;

    @Inject
    @Local
    public NavigatorHolder navigatorHolder;

    private Navigator navigator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentManager.getInstance().getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        bnvMenu.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(getNavigator());
    }

    @Override
    public void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (bnvMenu.getSelectedItemId() == item.getItemId()) return false;
        switch (item.getItemId()) {
            case R.id.menu_action_home:
                mainPresenter.openMainScreen();
                return true;

            case R.id.menu_action_favorites:
                mainPresenter.openFavoritesScreen();
                return true;

            case R.id.menu_action_info:
                mainPresenter.openInfoScreen();
                return true;

            default:
                return false;
        }
    }

    private Navigator getNavigator() {
        if (navigator == null) {
            navigator = new SupportFragmentNavigator(getChildFragmentManager(), R.id.fl_container) {
                @Override
                protected Fragment createFragment(String screenKey, Object data) {
                    return FragmentFactory.getFragmentByKey(screenKey, data);
                }

                @Override
                protected void showSystemMessage(String message) {

                }

                @Override
                protected void exit() {
                    mainPresenter.onBackPressed();
                }

            };
        }
        return navigator;
    }

    @Override
    public boolean onBackPressed() {
        mainPresenter.onBackPressed();
        return true;
    }
}
