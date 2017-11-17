package com.woolf.cleanapp.data.photo;

import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import java.util.List;

import io.reactivex.Flowable;

public interface IPhotoCache {

    Flowable<List<PhotoDomainModel>> getPhotos();
}
