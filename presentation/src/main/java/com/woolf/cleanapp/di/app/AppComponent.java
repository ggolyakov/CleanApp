package com.woolf.cleanapp.di.app;

import android.content.Context;

import com.woolf.cleanapp.data.IApiService;
import com.woolf.cleanapp.data.cache.ICache;
import com.woolf.cleanapp.di.app.module.ApiModule;
import com.woolf.cleanapp.di.app.module.CacheModule;
import com.woolf.cleanapp.di.app.module.ContextModule;
import com.woolf.cleanapp.di.app.module.NavigationModule;
import com.woolf.cleanapp.di.app.module.ThreadingModule;
import com.woolf.cleanapp.di.app.module.UtilsModule;
import com.woolf.cleanapp.di.photo.PhotoComponent;
import com.woolf.cleanapp.ui.main.MainActivity;
import com.woolf.cleanapp.ui.main.MainPresenter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Singleton
@Component(modules = {
        ApiModule.class,
        ContextModule.class,
        UtilsModule.class,
        ThreadingModule.class,
        CacheModule.class,
        NavigationModule.class})
public interface AppComponent {

    PhotoComponent.Builder photoComponentBuilder();

    void inject(MainActivity activity);

    void inject(MainPresenter mainPresenter);

    Context context();

    IApiService applicationApi();

    ICache cache();

    @Named(ThreadingModule.BACKGROUND_SCHEDULER)
    Scheduler backgroundScheduler();

    Router router();

    NavigatorHolder navigatorHolder();


}
