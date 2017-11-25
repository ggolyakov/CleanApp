package com.woolf.cleanapp.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.woolf.cleanapp.R;
import com.woolf.cleanapp.base.BaseActivity;
import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.util.FragmentFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class MainActivity extends BaseActivity implements IMainView, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bnv_menu)
    BottomNavigationView bnvMenu;

    @InjectPresenter
    MainPresenter mainPresenter;

    @Inject
    public NavigatorHolder navigatorHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ComponentManager.getInstance().getAppComponent().inject(this);
        bnvMenu.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (bnvMenu.getSelectedItemId() == item.getItemId()) return false;
        switch (item.getItemId()) {
            case R.id.menu_action_list:
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

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fl_container) {
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            return FragmentFactory.getFragmentByKey(screenKey, data);
        }

        @Override
        protected void showSystemMessage(String message) {

        }

        @Override
        protected void exit() {
            finish();
        }
    };
}
