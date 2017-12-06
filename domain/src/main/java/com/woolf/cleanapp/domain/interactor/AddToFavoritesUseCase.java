package com.woolf.cleanapp.domain.interactor;


import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.domain.interactor.base.completable.BaseCompletableUseCaseWithParams;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import io.reactivex.Completable;

public class AddToFavoritesUseCase extends BaseCompletableUseCaseWithParams<PhotoDomainModel> {

    private IPhotoRepository photoRepository;

    public AddToFavoritesUseCase(IPhotoRepository photoRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.photoRepository = photoRepository;
    }


    @Override
    protected Completable buildUseCase(PhotoDomainModel photoDomainModel) {
        return photoRepository.addToFavorite(photoDomainModel);
    }
}
