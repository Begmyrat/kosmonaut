package com.begmyratmammedov.rocketick.network;

import com.begmyratmammedov.rocketick.model.LaunchModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("v4/launches")
    Call<List<LaunchModel>> getLaunchList();

}
