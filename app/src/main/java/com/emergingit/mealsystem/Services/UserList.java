package com.emergingit.mealsystem.Services;

import com.emergingit.mealsystem.Models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserList {
    @GET("/users/all")
    Call<List<Users>> getUser();

    @POST("/users/signup")
    Call<List<Users>> postUser(@Body Users users);
}

