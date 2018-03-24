package com.wander.notes.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.wander.notes.data.model.Note;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class NoteRepository {
    private RESTService restService;

    @Inject
    public NoteRepository(RESTService restService) {
        this.restService = restService;
    }

    public LiveData<List<Note>> getNoteList(String userId) {
        final MutableLiveData<List<Note>> data = new MutableLiveData<>();

        restService.getNoteList(userId).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
