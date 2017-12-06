package com.woolf.cleanapp.domain.repository;


import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IPhotoRepository {

    Completable addToFavorite(PhotoDomainModel photo);

    Completable removeFromFavorite(String id);

    Single<PhotoDomainModel> getPhotoById(String id);
}
