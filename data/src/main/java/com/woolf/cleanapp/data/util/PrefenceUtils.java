package com.woolf.cleanapp.data.util;


import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.Scheduler;

public class PrefenceUtils implements IPreferenceUtils{

    private Context context;
    private SharedPreferences preferences;
    private Scheduler mainScheduler;
    private Scheduler backgroundScheduler;

    public PrefenceUtils(Context context, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        this.context = context;
        this.mainScheduler = mainScheduler;
        this.backgroundScheduler = backgroundScheduler;
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
