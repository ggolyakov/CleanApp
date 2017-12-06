package com.woolf.cleanapp.domain.interactor;


import com.woolf.cleanapp.domain.executor.IThreadExecutor;
import com.woolf.cleanapp.domain.interactor.base.single.BaseSingleUseCase;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;
import com.woolf.cleanapp.domain.repository.IPhotosRepository;

import java.util.List;

import io.reactivex.Single;

public class FavoritesUseCase extends BaseSingleUseCase<List<PhotoDomainModel>> {

    private IPhotosRepository photoRepository;

    public FavoritesUseCase(IPhotosRepository photoRepository,IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.photoRepository = photoRepository;
    }

    @Override
    protected Single<List<PhotoDomainModel>> buildUseCase() {
        return photoRepository.getFavorites();
    }
}
