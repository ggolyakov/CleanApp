package com.woolf.cleanapp.data.cache;

import com.woolf.cleanapp.data.model.cache.PhotoCacheModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface ICache {

    Single<List<PhotoCacheModel>> getFavorites();

    Single<PhotoCacheModel> getPhotoById(String id);

    Single<Boolean> isCached(String id);

    Completable addToFavorites(PhotoCacheModel model);

    Completable removeFromFavorites(String id);
}
