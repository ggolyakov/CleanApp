package com.woolf.cleanapp.di.app;

import com.woolf.cleanapp.di.app.module.ApiModule;
import com.woolf.cleanapp.di.app.module.CacheModule;
import com.woolf.cleanapp.di.app.module.ContextModule;
import com.woolf.cleanapp.di.app.module.NavigationModule;
import com.woolf.cleanapp.di.app.module.ThreadingModule;
import com.woolf.cleanapp.di.app.module.UtilsModule;
import com.woolf.cleanapp.di.photos.PhotosComponent;
import com.woolf.cleanapp.ui.info.InfoFragment;
import com.woolf.cleanapp.ui.main.MainActivity;
import com.woolf.cleanapp.ui.main.MainPresenter;
import com.woolf.cleanapp.ui.tab.TabFragment;
import com.woolf.cleanapp.ui.tab.TabPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class,
        ContextModule.class,
        UtilsModule.class,
        ThreadingModule.class,
        CacheModule.class,
        NavigationModule.class})
public interface AppComponent {

    PhotosComponent.Builder photosComponentBuilder();

    void inject(MainActivity activity);

    void inject(MainPresenter presenter);

    void inject(TabPresenter mainPresenter);

    void inject(TabFragment fragment);

    void inject(InfoFragment infoFragment);

}
