package com.emergingit.mealsystem.Services;

import com.emergingit.mealsystem.Models.AddReturn;
import com.emergingit.mealsystem.Models.Api.WeeklySummery;
import com.emergingit.mealsystem.Models.Api.weeklyData;
import com.emergingit.mealsystem.Models.Meals;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MealList {
    @POST("/meals/daily")
    Call<List<weeklyData>> getweeklyData();
    @POST("/meals/add")
    Call<AddReturn> addMeals(@Body Meals meals);
    @GET("/meals/weekly")
    Call<WeeklySummery> getweeklySummery();
}
