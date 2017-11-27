package com.woolf.cleanapp.domain.interactor;


import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.domain.interactor.base.BaseUseCaseWithParams;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import io.reactivex.Single;

public class RemoveFromFavoritesUseCase extends BaseUseCaseWithParams<Boolean, String> {

    private IPhotoRepository photoRepository;

    public RemoveFromFavoritesUseCase(IPhotoRepository photoRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.photoRepository = photoRepository;
    }

    @Override
    protected Single<Boolean> buildUseCaseObservable(String id) {
        return photoRepository.removeFromFavorite(id);
    }

}
