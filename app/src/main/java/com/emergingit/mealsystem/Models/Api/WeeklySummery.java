package com.emergingit.mealsystem.Models.Api;

import com.emergingit.mealsystem.Models.SumOfWeek;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeeklySummery {
    public SumOfWeek sumOfWeek;
    public List<weeklyData> weeklyData;
}
