package com.woolf.cleanapp.data.cache;


import com.woolf.cleanapp.data.model.cache.PhotoCacheModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmResults;

public class Cache implements ICache {

    @Override
    public Single<List<PhotoCacheModel>> getFavorites() {
        return Single.fromCallable(this::getFavoritesSync);
    }

    @Override
    public Single<PhotoCacheModel> getPhotoById(String id) {
        return Single.fromCallable(() -> getPhotoByIdSync(id));
    }

    @Override
    public Single<Boolean> isCached(String id) {
        return Single.fromCallable(() -> isCachedSync(id));
    }

    @Override
    public Completable addToFavorites(PhotoCacheModel model) {
        return Completable.fromCallable(() -> putSync(model));
    }

    @Override
    public Completable removeFromFavorites(String id) {
        return Completable.fromCallable(() -> removeSync(id));
    }


    private List<PhotoCacheModel> getFavoritesSync() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<PhotoCacheModel> results = realm.where(PhotoCacheModel.class).findAll();
        List<PhotoCacheModel> data = realm.copyFromRealm(results);
        realm.close();
        return data;
    }

    private PhotoCacheModel getPhotoByIdSync(String id) {
        PhotoCacheModel model = null;
        Realm realm = Realm.getDefaultInstance();
        PhotoCacheModel result = realm.where(PhotoCacheModel.class).equalTo("id", id).findFirst();
        if (result != null) {
            model = realm.copyFromRealm(result);
        }
        realm.close();
        return model;
    }

    private Boolean isCachedSync(String id) {
        return getPhotoByIdSync(id) != null;
    }

    private Boolean putSync(PhotoCacheModel model) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        try {
            realm.copyToRealmOrUpdate(model);
            realm.commitTransaction();
        } catch (Throwable throwable) {
            if (realm.isInTransaction()) {
                realm.cancelTransaction();
            }
            throw throwable;
        } finally {
            realm.close();
        }
        return true;
    }

    private Boolean removeSync(String id) {
        Realm realm = Realm.getDefaultInstance();
        PhotoCacheModel result = realm.where(PhotoCacheModel.class).equalTo("id", id).findFirst();
        if (result != null) {
            realm.beginTransaction();
            try {
                result.deleteFromRealm();
                realm.commitTransaction();
            } catch (Throwable throwable) {
                if (realm.isInTransaction()) {
                    realm.cancelTransaction();
                }
                throw throwable;
            } finally {
                realm.close();
            }
        }
        return true;
    }
}
