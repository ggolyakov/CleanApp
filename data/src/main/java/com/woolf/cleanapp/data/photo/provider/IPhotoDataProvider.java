package com.woolf.cleanapp.data.photo.provider;


import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public interface IPhotoDataProvider {
    Flowable<List<PhotoDomainModel>> getPhotos(Map<String, String> params);
}
