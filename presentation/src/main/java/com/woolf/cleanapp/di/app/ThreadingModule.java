package com.woolf.cleanapp.di.app;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ThreadingModule {

    public static final String MAIN_SCHEDULER = "main_scheduler";
    public static final String BACKGROUND_SCHEDULER = "background_scheduler";

    @Provides
    @Singleton
    @Named(ThreadingModule.MAIN_SCHEDULER)
    public Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(ThreadingModule.BACKGROUND_SCHEDULER)
    public Scheduler provideBackgroundScheduler() {
        return Schedulers.io();
    }
}
