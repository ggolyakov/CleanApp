package com.woolf.cleanapp.domain.interactor;


import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.domain.interactor.base.BaseUseCaseWithParams;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import java.util.HashMap;

import io.reactivex.Single;

public class PhotoByIdUseCase extends BaseUseCaseWithParams<PhotoDomainModel, PhotoByIdUseCase.Params> {

    private IPhotoRepository photoRepository;

    public PhotoByIdUseCase(IPhotoRepository photoRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.photoRepository = photoRepository;
    }

    @Override
    protected Single<PhotoDomainModel> buildUseCaseObservable(PhotoByIdUseCase.Params params) {
        return photoRepository.getPhotoById(params.id, params.options);
    }

    public static class Params {
        private String id;
        private HashMap<String, String> options;

        public Params(String id, HashMap<String, String> options) {
            this.id = id;
            this.options = options;
        }
    }
}
