package ru.romazanov.app_7862.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.romazanov.app_7862.model.PointResponse;

public interface Api {
    @GET("api/test/points")
    Call<PointResponse> getPoints(@Query("count") int count);
}

