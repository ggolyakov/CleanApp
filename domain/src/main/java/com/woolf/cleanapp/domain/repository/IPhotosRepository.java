package com.woolf.cleanapp.domain.repository;


import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public interface IPhotosRepository {

    Single<List<PhotoDomainModel>> getPhotos(Map<String, String> params);

    Single<List<PhotoDomainModel>> getFavorites();
}
