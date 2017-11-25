package com.woolf.cleanapp.domain.repository;


import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public interface IPhotoRepository {

    Flowable<List<PhotoDomainModel>> getPhotos(Map<String, String> params);

    Flowable<List<PhotoDomainModel>> getFavorites();

    Flowable<PhotoDomainModel> getPhotoById(String id, Map<String, String> params);
}
