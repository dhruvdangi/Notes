package com.wander.notes.di;

import com.wander.notes.RESTService;

import javax.inject.Singleton;

import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppModule {

    @Singleton
    @Provides
    RESTService provideRESTService() {
        return new Retrofit.Builder()
                .baseUrl(RESTService.REST_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RESTService.class);
    }
}
