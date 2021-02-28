package com.emergingit.mealsystem.Models;

import com.emergingit.mealsystem.Models.Meals;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddReturn {
    public Meals result;
    public boolean isAdded;
}

