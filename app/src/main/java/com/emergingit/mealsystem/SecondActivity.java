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
import com.emergingit.mealsystem.Models.Meals;
import com.google.android.material.appbar.MaterialToolbar;

import com.emergingit.mealsystem.Models.Api.weeklyData;
import com.emergingit.mealsystem.Services.MealList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
//    TextView email;
//    TextView password;

    List<weeklyData> weeklyDataList;
    RecyclerView recyclerView;

    MealList mealList;
    MaterialToolbar toolbar;

    Button btnUser;
//    TextView userId;

    TextView totalDailyMeal;

    FloatingActionButton fabAdd, fabUser,fabItem;
    Animation fabOpen,fabClose,fabClockwise,fabAntiClockwise;
    boolean isOpen = false;

    FloatingActionButton btnFab;
    Map<String,Integer> map = new HashMap<String, Integer>(){{
        put("One",1);
        put("Two",2);
        put("Three",3);
        put("Four",4);
    }};
    SwipeRefreshLayout swipeRefreshLayout;

    final int[] mealCount = {0};
    MyAdapter myAdapter = new MyAdapter(this);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

//        TextView dateToday = (TextView) findViewById(R.id.date_today);
//        dateToday.setText(currentDate);

//        userId = (TextView) findViewById(R.id.final_user_id);
        Intent iin = getIntent();
        Bundle bundle = iin.getExtras();
//        if(bundle != null){
//            String userid = (String) bundle.get("UserId");
//            userId.setText(userid);
//        }

        fabAdd = findViewById(R.id.fab_add);
        fabUser = findViewById(R.id.fab_user);
        fabItem = findViewById(R.id.fab_item);

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fabClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        fabAntiClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    fabUser.startAnimation(fabClose);
                    fabItem.startAnimation(fabClose);
                    fabAdd.startAnimation(fabClockwise);

                    fabUser.setClickable(false);
                    fabItem.setClickable(false);

                    isOpen = false;
                }
                else {
                    fabUser.startAnimation(fabOpen);
                    fabItem.startAnimation(fabOpen);
                    fabAdd.startAnimation(fabAntiClockwise);

                    fabUser.setClickable(true);
                    fabItem.setClickable(true);

                    isOpen = true;
                }
            }
        });

        toolbar =(MaterialToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recyclerView);
        mealList = Common.getMealList();

        totalDailyMeal = (TextView)findViewById(R.id.totaldailymeal);

        todaysRecord();

        //btnFab = findViewById(R.id.fab_btn);
        fabItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_spinner,null);
                builder.setTitle("Number of Meals");
                Spinner spinner = (Spinner) mView.findViewById(R.id.spinner);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.meals));
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!spinner.getSelectedItem().toString().equalsIgnoreCase("Choose a number")){
                            Toast.makeText(SecondActivity.this, spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                            String numberOfItems = spinner.getSelectedItem().toString();
                            int number = map.get(numberOfItems);
//                            Log.w("Meal Count", String.valueOf(weeklyDataList.get(0).getTotalMeal()+number));
//                            Log.w("User name", weeklyDataList.get(0).getUser().get(0).getUname());
                            String userId = (String) bundle.get("UserId");
                            int mealCount = number;
                            Meals meals = new Meals(userId,mealCount);
                            addMeal(meals);

                            Log.w("Hi bro,you ordered ", String.valueOf(meals.getMealCount()));
                            dialogInterface.dismiss();

                        }
                    }
                });

                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setView(mView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        btnUser = findViewById(R.id.btn_user);
        fabUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SecondActivity.this , UserActivity.class);
                startActivity(i);
            }
        });

//        swipeRefreshLayout = findViewById(R.id.refreshLayout);
//
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mealList.getweeklyData().enqueue(new Callback<List<weeklyData>>() {
//                    @Override
//                    public void onResponse(Call<List<weeklyData>> call, Response<List<weeklyData>> response) {
//                        if(response.isSuccessful()){
//                            Log.w("response",response.body().toString());
//                            weeklyDataList = response.body();
//
//                            myAdapter.setWeeklyDataList(weeklyDataList);
//                            myAdapter.notifyDataSetChanged();
//                        }else{
//                            Log.w("error",response.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<weeklyData>> call, Throwable t) {
//
//                    }
//                });
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void addMeal(Meals meals){
        mealList.addMeals(meals).enqueue(new Callback<AddReturn>() {
            @Override
            public void onResponse(Call<AddReturn> call, Response<AddReturn> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SecondActivity.this, "Meal record updeated..",Toast.LENGTH_SHORT).show();
                    todaysRecord();
                }else{
                    Log.w("error",response.toString());
                }
            }

            @Override
            public void onFailure(Call<AddReturn> call, Throwable t) {

            }
        });
    }

    public void todaysRecord(){
        mealCount[0]=0;
        mealList.getweeklyData().enqueue(new Callback<List<weeklyData>>() {
            @Override
            public void onResponse(Call<List<weeklyData>> call, Response<List<weeklyData>> response) {
                if(response.isSuccessful()){
                    Log.w("response",response.body().toString());
                    weeklyDataList = response.body();
                    for (int i=0;i<weeklyDataList.size();i++){
                        mealCount[0] += weeklyDataList.get(i).getTotalMeal();
                    }
                    totalDailyMeal.setText("Total Daily Meal Count : "+mealCount[0]);
                    myAdapter.setWeeklyDataList(weeklyDataList);
                    myAdapter.notifyDataSetChanged();
                }else{
                    Log.w("error",response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<weeklyData>> call, Throwable t) {
                Log.w("error",t.toString());
            }
        });
    }
}