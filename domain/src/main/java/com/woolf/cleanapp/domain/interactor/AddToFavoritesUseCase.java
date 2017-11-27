package com.woolf.cleanapp.domain.interactor;


import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.domain.interactor.base.BaseUseCaseWithParams;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import io.reactivex.Single;

public class AddToFavoritesUseCase extends BaseUseCaseWithParams<Boolean, PhotoDomainModel> {

    private IPhotoRepository photoRepository;

    public AddToFavoritesUseCase(IPhotoRepository photoRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.photoRepository = photoRepository;
    }


    @Override
    protected Single<Boolean> buildUseCaseObservable(PhotoDomainModel photoDomainModel) {
        return photoRepository.addToFavorite(photoDomainModel);
    }
}
