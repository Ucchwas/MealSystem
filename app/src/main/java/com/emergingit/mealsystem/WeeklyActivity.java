package com.emergingit.mealsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.emergingit.mealsystem.Models.AddReturn;
import com.emergingit.mealsystem.Models.Api.WeeklySummery;
import com.emergingit.mealsystem.Models.Meals;
import com.google.android.material.appbar.MaterialToolbar;

import com.emergingit.mealsystem.Models.Api.weeklyData;
import com.emergingit.mealsystem.Services.MealList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeeklyActivity extends AppCompatActivity {

    List<weeklyData> weeklyDataList;
    RecyclerView recyclerView;
    WeeklySummery weeklySummery;

    MealList mealList;
    MaterialToolbar toolbar;

    TextView totalWeeklyMeal;
    TextView totalmealcount;

    SwipeRefreshLayout swipeRefreshLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        toolbar =(MaterialToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        totalWeeklyMeal = (TextView) findViewById(R.id.totalweeklymeal);
        totalmealcount = (TextView) findViewById(R.id.totalmealcount);

        recyclerView = findViewById(R.id.recyclerView);
        mealList = Common.getMealList();


        MyAdapter myAdapter = new MyAdapter(this);
        mealList.getweeklySummery().enqueue(new Callback<WeeklySummery>() {
            @Override
            public void onResponse(Call<WeeklySummery> call, Response<WeeklySummery> response) {
                weeklySummery = response.body();
                totalWeeklyMeal.setText("Total Amount: "+weeklySummery.getSumOfWeek().getTotalAmount());
                totalmealcount.setText("Total Meal : "+weeklySummery.getSumOfWeek().getTotalMealCount());
                myAdapter.setWeeklyDataList(weeklySummery.getWeeklyData());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<WeeklySummery> call, Throwable t) {

            }
        });


        //totalWeeklyMeal.setText("50");

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