package com.emergingit.mealsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emergingit.mealsystem.Models.Users;
import com.emergingit.mealsystem.Services.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Login;
    private Button Register;

    UserList userList;
    List<Users> allUsersList;

    boolean matched;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText)findViewById(R.id.inputEmail);
        Password = (EditText)findViewById(R.id.inputPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        Register = (Button)findViewById(R.id.btnRegister);

        userList = Common.getUserList();
        UserAdapter userAdapter = new UserAdapter(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate(Email.getText().toString(), Password.getText().toString());
                userList.getUser().enqueue(new Callback<List<Users>>() {
                    @Override
                    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                        if(response.isSuccessful()){
                            Log.w("Users List : ", response.body().toString());
                            Log.w("Email : ", Email.getText().toString());
                            allUsersList = response.body();
                            for(int i=0;i<allUsersList.size();i++){
                                if(allUsersList.get(i).getEmail().equals(Email.getText().toString()) && allUsersList.get(i).getPassword().equals(Password.getText().toString())){
                                    Intent intent = new Intent(MainActivity.this , MealsActivity.class);
                                    intent.putExtra("UserId", allUsersList.get(i).getUserId());
                                    startActivity(intent);
                                    matched = true;
                                    break;
                                }
                            }
                            if(!matched){
                                Toast.makeText(MainActivity.this,"Give Correct Email & Password" ,Toast.LENGTH_SHORT).show();
                            }
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
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void validate(String email,String password){

        if(email.equals("ucc") && password.equals("1234")){
            Intent intent = new Intent(MainActivity.this , SecondActivity.class);
            intent.putExtra("em" , email);
            intent.putExtra("pass", password);
            startActivity(intent);
        }
    }
}