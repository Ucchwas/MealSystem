package com.emergingit.mealsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.emergingit.mealsystem.Models.Users;
import com.emergingit.mealsystem.Services.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    UserList userList;
    TextView uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userList = Common.getUserList();
        uname = (TextView)findViewById(R.id.uname);

        userList.getUser().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                Log.w("response",response.body().toString());
                List<Users> userListData=response.body();
                uname.setText(userListData.get(0).getUname());
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.e("error",t.toString());
            }
        });
    }
}