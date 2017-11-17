package com.woolf.cleanapp.di.app;


import android.content.Context;

import com.woolf.cleanapp.data.util.IPreferenceUtils;
import com.woolf.cleanapp.data.util.PrefenceUtils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    public IPreferenceUtils providePreferenceUtils(Context context,
                                                   @Named(ThreadingModule.MAIN_SCHEDULER) Scheduler mainScheduler,
                                                   @Named(ThreadingModule.BACKGROUND_SCHEDULER) Scheduler backgroundScheduler) {
        return new PrefenceUtils(context, mainScheduler, backgroundScheduler);
    }
}
