package com.woolf.cleanapp.data.photo.mapper;


import com.woolf.cleanapp.data.model.cache.PhotoCacheModel;
import com.woolf.cleanapp.data.model.service.PhotoEntity;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import java.util.List;

public interface IPhotoModelMapper {

    PhotoDomainModel mapEntityToDomain(PhotoEntity photo);

    List<PhotoDomainModel> mapEntityToDomainList(List<PhotoEntity> photos);

    PhotoDomainModel mapCacheToDomain(PhotoCacheModel photo);

    List<PhotoDomainModel> mapCacheToDomainList(List<PhotoCacheModel> photos);

    PhotoCacheModel mapDomainToCache(PhotoDomainModel photo);

    List<PhotoCacheModel> mapDomainToCacheList(List<PhotoDomainModel> photos);
}
