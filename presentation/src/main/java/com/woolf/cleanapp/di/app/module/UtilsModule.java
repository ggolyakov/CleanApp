package com.woolf.cleanapp.di.app.module;


import android.content.Context;

import com.woolf.cleanapp.data.util.IPreferenceUtils;
import com.woolf.cleanapp.data.util.PreferenceUtils;
import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.util.ResUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    public IPreferenceUtils providePreferenceUtils(Context context, IThreadExecutor threadExecutor) {
        return new PreferenceUtils(context, threadExecutor);
    }

    @Provides
    @Singleton
    public ResUtils provideResUtils(Context context) {
        return new ResUtils(context);
    }
}
