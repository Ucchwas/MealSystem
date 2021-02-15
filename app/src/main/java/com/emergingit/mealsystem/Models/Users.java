package com.emergingit.mealsystem.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users {
    private String userId;
    private String uname;
    private String password;
    private String email;
}
