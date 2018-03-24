package com.wander.notes.data.repository;

import com.wander.notes.data.model.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RESTService {
    String REST_API_URL = "https://api.wander.com/";

    @GET("")
    Call<List<Note>> getNoteList();

}
