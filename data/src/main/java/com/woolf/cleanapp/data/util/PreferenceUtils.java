package com.woolf.cleanapp.data.util;


import android.content.Context;
import android.content.SharedPreferences;

import com.woolf.cleanapp.domain.executor.IThreadExecutor;

public class PreferenceUtils implements IPreferenceUtils{

    private Context context;
    private SharedPreferences preferences;
    private IThreadExecutor threadExecutor;

    public PreferenceUtils(Context context, IThreadExecutor threadExecutor) {
        this.context = context;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public String getStringValue(String key) {
        return null;
    }

    @Override
    public int getIntValue(String key) {
        return 0;
    }

    @Override
    public long getLongValue(String key) {
        return 0;
    }

    @Override
    public boolean getBooleanValue(String key) {
        return false;
    }
}
