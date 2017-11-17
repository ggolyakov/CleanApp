package com.woolf.cleanapp.data.photo.mapper;


import com.woolf.cleanapp.data.model.service.PhotoEntity;
import com.woolf.cleanapp.domain.model.PhotoDomainModel;

import java.util.List;

public interface IPhotoModelMapper {

    PhotoDomainModel mapEntityToDomain(PhotoEntity photo);

    List<PhotoDomainModel> mapEntityToDomainList(List<PhotoEntity> photos);

}
