package com.woolf.cleanapp.domain.interactor;


import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.domain.interactor.base.BaseUseCaseWithParams;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotosRepository;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;

public class PhotosUseCase extends BaseUseCaseWithParams<List<PhotoDomainModel>, HashMap<String, String>> {

    private IPhotosRepository photoRepository;

    public PhotosUseCase(IPhotosRepository photoRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.photoRepository = photoRepository;
    }

    @Override
    protected Single<List<PhotoDomainModel>> buildUseCaseObservable(HashMap<String, String> params) {
        return photoRepository.getPhotos(params);
    }
}
