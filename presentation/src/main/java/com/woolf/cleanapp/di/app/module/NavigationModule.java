package com.woolf.cleanapp.di.app.module;

import com.woolf.cleanapp.di.app.qualifier.Global;
import com.woolf.cleanapp.di.app.qualifier.Local;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class NavigationModule {

    private Cicerone<Router> localCicerone;
    private Cicerone<Router> globalCicerone;

    public NavigationModule() {
        localCicerone = Cicerone.create();
        globalCicerone = Cicerone.create();
    }

    @Provides
    @Singleton
    @Local
    Router provideLocalRouter() {
        return localCicerone.getRouter();
    }

    @Provides
    @Singleton
    @Local
    NavigatorHolder provideLocalNavigatorHolder() {
        return localCicerone.getNavigatorHolder();
    }

    @Provides
    @Singleton
    @Global
    Router provideGlobalRouter() {
        return globalCicerone.getRouter();
    }

    @Provides
    @Singleton
    @Global
    NavigatorHolder provideGlobalNavigatorHolder() {
        return globalCicerone.getNavigatorHolder();
    }
}
