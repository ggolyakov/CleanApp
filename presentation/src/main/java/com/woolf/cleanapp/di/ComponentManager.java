package com.woolf.cleanapp.di;


import com.woolf.cleanapp.CleanApplication;
import com.woolf.cleanapp.di.app.AppComponent;
import com.woolf.cleanapp.di.app.module.ContextModule;
import com.woolf.cleanapp.di.app.DaggerAppComponent;
import com.woolf.cleanapp.di.photo.PhotoComponent;
import com.woolf.cleanapp.di.photo.PhotoRepositoryModule;

public class ComponentManager {
    private static ComponentManager componentManager;

    private AppComponent appComponent;
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

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public PhotoComponent getPhotoComponent() {
        if (photoComponent == null) {
            photoComponent = getAppComponent().photoComponentBuilder().scModule(new PhotoRepositoryModule()).build();
        }
        return photoComponent;
    }

    public void destroyPhotoComponent() {
        photoComponent = null;
    }
}
