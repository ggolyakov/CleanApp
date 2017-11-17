package com.woolf.cleanapp.domain.interactor;


import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;

public class PhotosUseCase extends BaseUseCase<List<PhotoDomainModel>, HashMap<String, String>> {

    private IPhotoRepository photoRepository;

    public PhotosUseCase(IPhotoRepository photoRepository, Scheduler thread) {
        super(thread);
        this.photoRepository = photoRepository;
    }

    @Override
    Flowable<List<PhotoDomainModel>> buildUseCaseObservable(HashMap<String, String> params) {
        return photoRepository.getPhotos(params);
    }
}
