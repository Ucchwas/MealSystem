package com.emergingit.mealsystem;

import com.emergingit.mealsystem.Services.MealList;
import com.emergingit.mealsystem.Services.RetrofitClient;
import com.emergingit.mealsystem.Services.UserList;

public class Common {
    private static final String baseUrl = "https://mealrecord.herokuapp.com/";

    public static UserList getUserList(){
        return RetrofitClient.getClient(baseUrl).create(UserList.class);
    }

    public static MealList getMealList(){
        return RetrofitClient.getClient(baseUrl).create(MealList.class);
    }
}
