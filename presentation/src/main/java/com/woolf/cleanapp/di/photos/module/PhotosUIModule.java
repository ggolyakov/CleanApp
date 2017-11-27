package com.woolf.cleanapp.di.photos.module;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.woolf.cleanapp.di.photos.PhotosScope;
import com.woolf.cleanapp.util.adapter.PhotoAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotosUIModule {
    private static final int SPAN_COUNT = 2;

    @Provides
    @PhotosScope
    PhotoAdapter providePhotoAdapter() {
        return new PhotoAdapter();
    }

    @Provides
    LinearLayoutManager provideLinearLayotManager(Context context) {
        return new LinearLayoutManager(context);
    }
    @Provides
    GridLayoutManager provodeGridLayoutManager(Context context) {
        return new GridLayoutManager(context, SPAN_COUNT);
    }
}
