package com.woolf.cleanapp.domain.interactor;


import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.domain.interactor.base.completable.BaseCompletableUseCaseWithParams;
import com.woolf.cleanapp.domain.repository.IPhotoRepository;

import io.reactivex.Completable;

public class RemoveFromFavoritesUseCase extends BaseCompletableUseCaseWithParams<String> {

    private IPhotoRepository photoRepository;

    public RemoveFromFavoritesUseCase(IPhotoRepository photoRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.photoRepository = photoRepository;
    }

    @Override
    protected Completable buildUseCase(String id) {
        return photoRepository.removeFromFavorite(id);
    }

}
