package com.emergingit.mealsystem.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddUsers {
    public Users users;
    public boolean isAdded;
}
