package com.woolf.cleanapp.di.app;

import android.content.Context;

import com.woolf.cleanapp.CleanApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private CleanApplication application;

    public ContextModule(CleanApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public CleanApplication provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return application;
    }
}
