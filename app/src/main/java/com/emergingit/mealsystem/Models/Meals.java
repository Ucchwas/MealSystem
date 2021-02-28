package com.emergingit.mealsystem.Models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Meals {
    private String mealId;
    private String userId;
    private Date date;
    private int mealCount;
    private int price;

    public Meals(String userId, int mealCount) {
        this.userId = userId;
        this.mealCount = mealCount;
    }
}
