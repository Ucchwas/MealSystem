package com.emergingit.mealsystem.Services;

import com.emergingit.mealsystem.Models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserList {
    @GET("/users/all")
    Call<List<Users>> getUser();
}

