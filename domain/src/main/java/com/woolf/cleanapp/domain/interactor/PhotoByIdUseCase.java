package com.woolf.cleanapp.domain.interactor;


import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.domain.interactor.base.single.BaseSingleUseCaseWithParams;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import io.reactivex.Single;

public class PhotoByIdUseCase extends BaseSingleUseCaseWithParams<PhotoDomainModel, String> {

    private IPhotoRepository photoRepository;

    public PhotoByIdUseCase(IPhotoRepository photoRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.photoRepository = photoRepository;
    }

    @Override
    protected Single<PhotoDomainModel> buildUseCase(String id) {
        return photoRepository.getPhotoById(id);
    }

}
