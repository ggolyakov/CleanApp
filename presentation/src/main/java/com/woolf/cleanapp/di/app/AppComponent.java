package com.woolf.cleanapp.di.app;

import android.content.Context;

import com.woolf.cleanapp.MainActivity;
import com.woolf.cleanapp.data.IApiService;
import com.woolf.cleanapp.di.photo.PhotoComponent;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;

@Singleton
@Component(modules = {
        ApiModule.class,
        ContextModule.class,
        UtilsModule.class,
        ThreadingModule.class})
public interface AppComponent {

    PhotoComponent.Builder photoComponentBuilder();

    void inject(MainActivity activity);

    Context context();

    IApiService applicationApi();

    @Named(ThreadingModule.BACKGROUND_SCHEDULER)
    Scheduler backgroundScheduler();


}
