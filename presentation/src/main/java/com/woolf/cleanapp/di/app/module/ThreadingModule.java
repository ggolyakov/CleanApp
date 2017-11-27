package com.woolf.cleanapp.di.app.module;


import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.util.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ThreadingModule {

    @Provides
    @Singleton
    public IThreadExecutor provideBackgroundScheduler() {
        return new ThreadExecutor();
    }
}
