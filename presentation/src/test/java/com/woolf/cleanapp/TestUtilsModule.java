package com.woolf.cleanapp;


import android.content.Context;

import com.woolf.cleanapp.data.util.IPreferenceUtils;
import com.woolf.cleanapp.di.app.module.UtilsModule;
import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.util.ResUtils;

import static org.mockito.Mockito.mock;

public class TestUtilsModule extends UtilsModule {
    @Override
    public IPreferenceUtils providePreferenceUtils(Context context, IThreadExecutor threadExecutor) {
        return mock(IPreferenceUtils.class);
    }

    @Override
    public ResUtils provideResUtils(Context context) {
        return mock(ResUtils.class);
    }
}
