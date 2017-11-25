package com.woolf.cleanapp.data.cache;

import com.woolf.cleanapp.data.model.cache.PhotoCacheModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ICache {

    Flowable<List<PhotoCacheModel>> getFavorites();

    Flowable<PhotoCacheModel> getPhotoById(String id);

    Flowable<Boolean> isCached(String id);

    Completable put(PhotoCacheModel model);

    Flowable<PhotoCacheModel> remove(String id);
}
