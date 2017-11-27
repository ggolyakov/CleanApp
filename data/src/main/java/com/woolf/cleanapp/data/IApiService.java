package com.woolf.cleanapp.data;


import com.woolf.cleanapp.data.model.service.PhotoEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface IApiService {

    @GET("photos")
    Single<List<PhotoEntity>> getPhotos(@QueryMap Map<String, String> options);

    @GET("photos/{id}")
    Single<PhotoEntity> getPhotoById(@Path("id") String id, @QueryMap Map<String, String> options);
}
