package com.woolf.cleanapp.di;


import android.support.annotation.VisibleForTesting;

import com.woolf.cleanapp.CleanApplication;
import com.woolf.cleanapp.di.app.AppComponent;
import com.woolf.cleanapp.di.app.DaggerAppComponent;
import com.woolf.cleanapp.di.app.module.ContextModule;
import com.woolf.cleanapp.di.photo.PhotoComponent;
import com.woolf.cleanapp.di.photos.PhotosComponent;

public class ComponentManager {
    private static ComponentManager componentManager;

    private AppComponent appComponent;
    private PhotosComponent photosComponent;
    private PhotoComponent photoComponent;

    public static ComponentManager getInstance() {
        if (componentManager == null) {
            componentManager = new ComponentManager();
        }
        return componentManager;
    }

    public void initAppComponent(CleanApplication application) {
        appComponent = DaggerAppComponent.builder().contextModule(new ContextModule(application)).build();
    }
    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent){
        this.appComponent = appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public PhotosComponent getPhotosComponent() {
        if (photosComponent == null) {
            photosComponent = getAppComponent().photosComponentBuilder().build();
        }
        return photosComponent;
    }

    public PhotoComponent getPhotoComponent() {
        if (photoComponent == null) {
            photoComponent = getPhotosComponent().photoComponentBuilder().build();
        }
        return photoComponent;
    }

    public void destroyPhotosComponent() {
        photosComponent = null;
    }

    public void destroyPhotoComponent() {
        photoComponent = null;
    }
}
