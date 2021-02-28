package com.emergingit.mealsystem.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SumOfWeek {
    private double totalAmount;
    private double totalMealCount;
}
