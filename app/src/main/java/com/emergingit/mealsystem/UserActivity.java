package com.emergingit.mealsystem;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emergingit.mealsystem.Models.Api.AllUsers;
import com.emergingit.mealsystem.Models.Users;
import com.emergingit.mealsystem.Services.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserList userList;

    List<Users> allUsersList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        recyclerView = findViewById(R.id.userRecyclerview);
        userList = Common.getUserList();

        UserAdapter userAdapter = new UserAdapter(this);

        userList.getUser().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(response.isSuccessful()){
                    Log.w("Users List : ", response.body().toString());
                    allUsersList = response.body();
                    userAdapter.setUsersList(allUsersList);
                    userAdapter.notifyDataSetChanged();
                }
                else {
                    Log.w("Error", response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);
    }
}

