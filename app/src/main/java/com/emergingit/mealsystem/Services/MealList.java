package com.emergingit.mealsystem.Services;

import com.emergingit.mealsystem.Models.Api.weeklyData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealList {
    @GET("/meals/weekly")
    Call<List<weeklyData>> getweeklyData();
}
