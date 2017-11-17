package com.woolf.cleanapp;


import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.woolf.cleanapp.di.ComponentManager;

public class CleanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        ComponentManager.getInstance().initAppComponent(this);
    }
}
