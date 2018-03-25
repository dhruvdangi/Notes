package com.wander.notes.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.wander.notes.data.repository.DBService;
import com.wander.notes.data.repository.MockApiImpl;
import com.wander.notes.data.repository.RESTService;
import com.wander.notes.viewmodel.NoteListViewModelFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {

    @Singleton
    @Provides
    OkHttpClient providesOkHttpClient(Application application) {
        return new OkHttpClient.Builder()
//            .addInterceptor(new OkHttpMockInterceptor(application.getBaseContext(), 5))
            .build();
    }

    @Singleton
    @Provides
    DBService provideDBService(Application application) {
        return Room.databaseBuilder(application, DBService.class, "note-db").build();
    }

    @Singleton
    @Provides
    RESTService provideRESTService(OkHttpClient okHttpClient) {

        NetworkBehavior networkBehavior = NetworkBehavior.create();
        networkBehavior.setFailurePercent(0);
        networkBehavior.setDelay(1, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RESTService.REST_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build();
        BehaviorDelegate<RESTService> behaviorDelegate= mockRetrofit.create(RESTService.class);
        return new MockApiImpl(behaviorDelegate);

//        return new Retrofit.Builder()
//                .baseUrl(RESTService.REST_API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build()
//                .create(RESTService.class);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new NoteListViewModelFactory(viewModelSubComponent.build());
    }

}
