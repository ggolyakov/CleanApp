package com.woolf.cleanapp.domain.interactor;


import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;

public class FavoritesUseCase extends BaseUseCase<List<PhotoDomainModel>, HashMap<String, String>> {

    private IPhotoRepository photoRepository;

    public FavoritesUseCase(IPhotoRepository photoRepository, Scheduler mainThread, Scheduler backgroundThread) {
        super(mainThread, backgroundThread);
        this.photoRepository = photoRepository;
    }

    @Override
    Flowable<List<PhotoDomainModel>> buildUseCaseObservable(HashMap<String, String> params) {
        return photoRepository.getFavorites();
    }
}
