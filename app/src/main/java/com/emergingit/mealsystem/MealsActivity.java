package com.emergingit.mealsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emergingit.mealsystem.Models.Users;
import com.emergingit.mealsystem.Services.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsActivity extends AppCompatActivity {
    private Button btnWeeklymeal,btnTodaysmeal;

//    TextView userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

//        userId = (TextView) findViewById(R.id.user_id);
        Intent iin = getIntent();
        Bundle bundle = iin.getExtras();
//        if(bundle != null){
//            String userid = (String) bundle.get("UserId");
//            userId.setText(userid);
//        }


        btnWeeklymeal = (Button) findViewById(R.id.btn_weeklymeal);
        btnTodaysmeal = (Button) findViewById(R.id.btn_todaysmeal);

        btnWeeklymeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MealsActivity.this , WeeklyActivity.class);
                startActivity(intent);
            }
        });

        btnTodaysmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MealsActivity.this , SecondActivity.class);
                intent.putExtra("UserId", (String)bundle.get("UserId"));
                startActivity(intent);
            }
        });
    }
}