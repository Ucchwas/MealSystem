package com.emergingit.mealsystem.Models.Api;

import com.emergingit.mealsystem.Models.Users;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class weeklyData {
    private int totalMeal;
    private int totalPrice;
    private List<Users> user;
}
