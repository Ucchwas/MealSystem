package com.emergingit.mealsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

import com.emergingit.mealsystem.Models.Api.weeklyData;
import com.emergingit.mealsystem.Models.Users;
import com.emergingit.mealsystem.Services.MealList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
//    TextView email;
//    TextView password;

    List<weeklyData> weeklyDataList;
    RecyclerView recyclerView;

    MealList mealList;
    MaterialToolbar toolbar ;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        toolbar =(MaterialToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recyclerView);
        mealList = Common.getMealList();

        MyAdapter myAdapter = new MyAdapter(this);
        mealList.getweeklyData().enqueue(new Callback<List<weeklyData>>() {
            @Override
            public void onResponse(Call<List<weeklyData>> call, Response<List<weeklyData>> response) {
                if(response.isSuccessful()){
                    Log.w("response",response.body().toString());
                    weeklyDataList = response.body();
                    myAdapter.setWeeklyDataList(weeklyDataList);
                    myAdapter.notifyDataSetChanged();
                }else{
                    Log.w("error",response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<weeklyData>> call, Throwable t) {

            }
        });


        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}