package com.example.androidapp.api.service;

import com.example.androidapp.commons.models.DevResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface StackOverflowService {

    @GET("users")
    Single<DevResponse> getDevelopers(@QueryMap Map<String, String> queries);
}
