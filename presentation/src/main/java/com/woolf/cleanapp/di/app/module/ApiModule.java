package com.woolf.cleanapp.di.app.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.woolf.cleanapp.data.Environment;
import com.woolf.cleanapp.data.IApiService;
import com.woolf.cleanapp.util.ResUtils;
import com.woolf.cleanapp.util.helper.ErrorHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Singleton
    @Provides
    public IApiService provideApiService() {
        final Retrofit retrofit = initRetrofit();
        return retrofit.create(IApiService.class);
    }

    @Singleton
    @Provides
    public ErrorHandler provideErrorHandler(ResUtils utils) {
        return new ErrorHandler(utils);
    }

    private Retrofit initRetrofit() {
        final Retrofit.Builder retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(Environment.BASE_URL)
                        .addConverterFactory(initConverterFactory())
                        .addCallAdapterFactory(initCallAdapterFactory())
                        .client(initOkHttpClient());
        return retrofitBuilder.build();
    }

    private Converter.Factory initConverterFactory() {

        return GsonConverterFactory.create();
    }

    private CallAdapter.Factory initCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    private OkHttpClient initOkHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader(Environment.HEADER_ACCEPT_VERSION, Environment.HEADER_ACCEPT_VERSION_VALUE);
            return chain.proceed(builder.build());
        });
        return httpClient.build();
    }

}
