package com.emergingit.mealsystem.Models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Meals {
    private int mealId;
    private int userId;
    private Date date;
    private int mealCount;
    private int price;

}
