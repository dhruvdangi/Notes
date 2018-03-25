package com.wander.notes.data.repository;

import com.wander.notes.data.model.Note;


import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RESTService {
    String REST_API_URL = "https://api.wander.com/";

    @GET("/user/{userId}")
    Call<List<Note>> getNoteList(@Path("userId") String userId);

    @POST("/user/{userId}")
    Call<JSONObject> postNote(@Path("userId") String userId);
}
