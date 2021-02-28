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

    public Users(String uname,String email,String password) {
        this.uname = uname;
        this.password = password;
        this.email = email;
    }
}
